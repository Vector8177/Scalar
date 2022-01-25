// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class TurnFull extends CommandBase {
    private final double degrees;

  /** Creates a new ArcadeDrive. */
  public TurnFull(double degreess) {
      degrees = degreess;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.driveTrain.changeMode();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(degrees > 0){
        Robot.driveTrain.setLeftMotors(RobotMap.AUTONOMOUS_SPEED);
        Robot.driveTrain.setRightMotors(-RobotMap.AUTONOMOUS_SPEED);
      } else if(degrees < 0){
        Robot.driveTrain.setLeftMotors(-RobotMap.AUTONOMOUS_SPEED);
        Robot.driveTrain.setRightMotors(RobotMap.AUTONOMOUS_SPEED); 
      }
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
    if(degrees > 0){
    return !(Robot.ahrs.getYaw() + (95 * RobotMap.AUTONOMOUS_SPEED) < degrees);
    } else if(degrees < 0){
        return !(Robot.ahrs.getYaw() - (95 * RobotMap.AUTONOMOUS_SPEED) > -degrees);
    } else{
        return true;
    }
  }
}
