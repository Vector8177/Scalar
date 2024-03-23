// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveElevatorEnc extends CommandBase {
    double rotations;
    double power;


    /** Creates a new ArcadeDrive. */
    public MoveElevatorEnc(double rotations, double power) {
        this.rotations = rotations;
        this.power = power;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.intake.resetEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        
            Robot.intake.setElevatorMotor(power);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.intake.setElevatorMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return rotations * RobotMap.Intake.ELEVATOR_ROTATION_TO_ENCODER < Math.abs(Robot.intake.elevatorEncoderDegrees());
    }
}
