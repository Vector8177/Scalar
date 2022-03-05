// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShootBall extends CommandBase {
    double backMotor;
    double frontMotor;
    double time;
    Timer timer = null;

    /** Creates a new ArcadeDrive. */
    public ShootBall(double backMotor, double frontMotor, double time) {
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
        Robot.shooter.setBackMotor(backMotor);
        Robot.shooter.setFrontMotor(frontMotor);
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
