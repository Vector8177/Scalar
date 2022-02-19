// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotMap;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.MathUtil;

public class TurnToBall extends CommandBase {
  double targetYaw = Robot.limelight.getYaw();
  public PIDController pid = new PIDController(RobotMap.kP, RobotMap.kI, RobotMap.kD);

  /** Creates a new ArcadeDrive. */
  public TurnToBall() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.ahrs.zeroYaw();
    pid.setTolerance(2, 5);
    pid.enableContinuousInput(-180, 180);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putData("PID Controller 2", pid);
    if (Robot.limelight.targetAvailable()) {
      targetYaw = Robot.limelight.getYaw();
      float yaw = 0;

      turnPID(yaw, targetYaw, Robot.m_oi.getAutoSpeed());
    } else {
      Robot.driveTrain.setLeftMotors(0);
      Robot.driveTrain.setRightMotors(0);
    }

  }

  public void turnPID(double yaw, double degrees, double autoSpeed) {
    Robot.driveTrain.setLeftMotors(MathUtil.clamp(pid.calculate(yaw, degrees), -autoSpeed, autoSpeed));
    Robot.driveTrain.setRightMotors(-MathUtil.clamp(pid.calculate(yaw, degrees), -autoSpeed, autoSpeed));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
