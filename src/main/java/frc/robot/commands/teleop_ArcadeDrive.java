// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class teleop_ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  public teleop_ArcadeDrive() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Controller Values
    double rightTrigger = Robot.m_oi.getDriverRightTrigger();
    double leftTrigger = Robot.m_oi.getDriverLeftTrigger();
    double leftStickX = Robot.m_oi.getDriverRawJoystick();
    boolean bButtonPressed = Robot.m_oi.bDriverButtonPressed();

    // Slowed down if B is pressed
    if (bButtonPressed) {
      rightTrigger /= 4.0;
      leftTrigger /= 4.0;
      leftStickX /= 3.0;
    }

    // Speed Calculations
    double leftMotorSpeed = ((rightTrigger * RobotMap.TURN_SPEED_MODIFIER) * Robot.m_oi.getTeleopSpeed())
        - ((leftTrigger * RobotMap.TURN_SPEED_MODIFIER) * Robot.m_oi.getTeleopSpeed());
    double rightMotorSpeed = ((rightTrigger * RobotMap.TURN_SPEED_MODIFIER) * Robot.m_oi.getTeleopSpeed())
        - ((leftTrigger * RobotMap.TURN_SPEED_MODIFIER) * Robot.m_oi.getTeleopSpeed());

    // Determining if robot should use joystick full-turning
    if (rightTrigger == 0 && leftTrigger == 0 && leftStickX != 0) {
      if (leftStickX > 0) {
        Robot.driveTrain.setLeftMotors(Robot.m_oi.getTeleopSpeed() * Math.abs(leftStickX));
        Robot.driveTrain.setRightMotors(-(Robot.m_oi.getTeleopSpeed() * Math.abs(leftStickX)));
      } else if (leftStickX < 0) {
        Robot.driveTrain.setRightMotors(Robot.m_oi.getTeleopSpeed() * Math.abs(leftStickX));
        Robot.driveTrain.setLeftMotors(-(Robot.m_oi.getTeleopSpeed() * Math.abs(leftStickX)));
      }
    }

    // Performing motor turning
    else if (leftStickX > 0) {
      System.out.println(leftMotorSpeed + " " + rightMotorSpeed);
      if (rightTrigger < leftTrigger) {
        Robot.driveTrain.setLeftMotors(
            rightMotorSpeed - (rightMotorSpeed * Math.abs(leftStickX) * RobotMap.LEFT_JOYSTICK_SPEED_MODIFIER));
        Robot.driveTrain.setRightMotors(leftMotorSpeed);
      } else {
        Robot.driveTrain.setLeftMotors(leftMotorSpeed);
        Robot.driveTrain.setRightMotors(
            rightMotorSpeed - (rightMotorSpeed * Math.abs(leftStickX) * RobotMap.LEFT_JOYSTICK_SPEED_MODIFIER));
      }
    } else if (leftStickX <= 0) {
      System.out.println(leftMotorSpeed + " " + rightMotorSpeed);
      if (rightTrigger < leftTrigger) {
        Robot.driveTrain.setLeftMotors(rightMotorSpeed);
        Robot.driveTrain.setRightMotors(
            leftMotorSpeed - (leftMotorSpeed * (Math.abs(leftStickX) * RobotMap.LEFT_JOYSTICK_SPEED_MODIFIER)));
      } else {
        Robot.driveTrain.setLeftMotors(
            leftMotorSpeed - (leftMotorSpeed * (Math.abs(leftStickX) * RobotMap.LEFT_JOYSTICK_SPEED_MODIFIER)));
        Robot.driveTrain.setRightMotors(rightMotorSpeed);
      }
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
