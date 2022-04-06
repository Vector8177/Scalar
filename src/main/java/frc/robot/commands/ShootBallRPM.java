// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShootBallRPM extends CommandBase {
    double backMotor;
    double frontMotor;
    double time;
    Timer timer = null;

    /** Creates a new ArcadeDrive. */
    public ShootBallRPM(double backMotor, double frontMotor, double time) {
        this.backMotor = backMotor;
        this.frontMotor = frontMotor;
        this.time = time;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
        Robot.shooter.motorSetup();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double frontRPMtoUnits = RobotMap.convertRPMToMotorVel(frontMotor);
        double backRPMtoUnits = RobotMap.convertRPMToMotorVel(backMotor);

        Robot.shooter.setFrontMotorV(frontRPMtoUnits);
        Robot.shooter.setBackMotorV(backRPMtoUnits);
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
