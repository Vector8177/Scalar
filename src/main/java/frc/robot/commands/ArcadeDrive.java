// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  public ArcadeDrive() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Controller Values
    double rightTrigger = Robot.m_oi.GetDriverRightTrigger();
    double leftTrigger = Robot.m_oi.GetDriverLeftTrigger();
    double leftX = Robot.m_oi.GetDriverRawJoystick(0);

    // Speed Calculations
    double leftMotorSpeed = (rightTrigger*RobotMap.LEFT_MOTOR_SPEED_MODIFIER) - (leftTrigger*RobotMap.LEFT_MOTOR_SPEED_MODIFIER);
    double rightMotorSpeed = (rightTrigger*RobotMap.RIGHT_MOTOR_SPEED_MODIFIER) - (leftTrigger*RobotMap.RIGHT_MOTOR_SPEED_MODIFIER);
    
    // Determining if robot should use pure joystick control
    if(rightTrigger == 0 && leftTrigger == 0 && leftX != 0){
      if(leftX > 0){
        Robot.driveTrain.setLeftMotors(RobotMap.LEFT_MOTOR_SPEED_MODIFIER * Math.abs(leftX));
        Robot.driveTrain.setRightMotors(-(RobotMap.RIGHT_MOTOR_SPEED_MODIFIER * Math.abs(leftX)));
      } else if(leftX < 0){
        Robot.driveTrain.setRightMotors(RobotMap.RIGHT_MOTOR_SPEED_MODIFIER * Math.abs(leftX));
        Robot.driveTrain.setLeftMotors(-(RobotMap.LEFT_MOTOR_SPEED_MODIFIER * Math.abs(leftX)));
      }
    }
    else if(leftX > 0){
      Robot.driveTrain.setLeftMotors(leftMotorSpeed);
      Robot.driveTrain.setRightMotors(rightMotorSpeed - (rightMotorSpeed*Math.abs(leftX) * RobotMap.LEFT_JOYSTICK_SPEED_MODIFIER));
    } else if(leftX <= 0){
      Robot.driveTrain.setLeftMotors(leftMotorSpeed - (leftMotorSpeed*(Math.abs(leftX) * RobotMap.LEFT_JOYSTICK_SPEED_MODIFIER)));
      Robot.driveTrain.setRightMotors(rightMotorSpeed);
    }
    
    System.out.print("Motor Degree: " + Robot.driveTrain.encoderDegrees() + " ");

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
    return false;
  }
}