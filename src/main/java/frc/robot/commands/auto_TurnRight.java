// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class auto_TurnRight extends CommandBase {

  Boolean isFinished = false;

  /** Creates a new ArcadeDrive. */
  public auto_TurnRight() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.print("Motor Degrese: ");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Robot.driveTrain.setAutoMotorsForward(2);
    // end(true);
    Robot.ahrs.zeroYaw();
    Robot.driveTrain.setAutoMotorsRight(90);
    isFinished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.driveTrain.setRightMotors(0);
    Robot.driveTrain.setLeftMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}