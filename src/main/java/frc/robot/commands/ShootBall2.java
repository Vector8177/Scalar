// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShootBall2 extends CommandBase {
    double backMotor;
    double frontMotor;
    double time;
    double frontPidCalc;
    double backPidCalc;
    Timer timer = null;
    PIDController frontPid = new PIDController(.0004, 0.0012, 0);
    PIDController backPid = new PIDController(.0004, 0.0012, 0);

    /** Creates a new ArcadeDrive. */
    public ShootBall2(double backMotor, double frontMotor, double time) {
        this.backMotor = backMotor;
        this.frontMotor = frontMotor;
        this.time = time;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        frontPidCalc = MathUtil.clamp(frontPid.calculate(Robot.shooter.getFrontMotorVelocity()*600/2048, frontMotor), -1, 1);
        backPidCalc = MathUtil.clamp(backPid.calculate(Robot.shooter.getBackMotorVelocity()*600/2048, backMotor), -1, 1);
        SmartDashboard.putData("back Shooting PID", backPid);
        SmartDashboard.putData("front Shooting PID", frontPid);
        Robot.shooter.setBackMotor(backPidCalc);
        Robot.shooter.setFrontMotor(frontPidCalc);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.shooter.setBackMotor(0);
        Robot.shooter.setFrontMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }
}
