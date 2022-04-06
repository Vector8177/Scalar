// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class teleop_ArcadeDrive extends CommandBase {
  SlewRateLimiter ramp = new SlewRateLimiter(2);

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

    double speed = (rightTrigger - leftTrigger) * Robot.m_oi.getTeleopSpeed();

    double rampSpeed = ramp.calculate(speed);
    double turnSpeed = (leftStickX * RobotMap.DriveTrain.TURN_SPEED_MODIFIER);

    Robot.driveTrain.motors.curvatureDrive(rampSpeed, turnSpeed, leftTrigger == 0);
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
