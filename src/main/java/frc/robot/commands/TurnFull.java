// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnFull extends CommandBase {
  private final double degrees;
  private final double yaw;
  PIDController pid = new PIDController(RobotMap.kP, RobotMap.kI, RobotMap.kD);
  private double pidcalc;

  /** Creates a new ArcadeDrive. */
  public TurnFull(double degreess, double yaw) {
    degrees = degreess;
    this.yaw = yaw;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid.setTolerance(5, 10);
    pid.enableContinuousInput(-180, 180);
    Timer.delay(.1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pidcalc = MathUtil.clamp(pid.calculate(yaw, degrees), -Robot.m_oi.getAutoSpeed(), Robot.m_oi.getAutoSpeed());
    SmartDashboard.putNumber("PID Output", pidcalc);
    SmartDashboard.putData("PID Controller", pid);
    Robot.driveTrain.setLeftMotors(pidcalc);
    Robot.driveTrain.setRightMotors(-pidcalc);
  }

  public double getPIDOutput() {
    return pidcalc;
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
    return true;
  }
}
