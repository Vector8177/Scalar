// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  public WPI_TalonFX frontRight = new WPI_TalonFX(RobotMap.FRONT_RIGHT_MOTOR_ID);
  public WPI_TalonFX backRight = new WPI_TalonFX(RobotMap.BACK_RIGHT_MOTOR_ID);
  public WPI_TalonFX frontLeft = new WPI_TalonFX(RobotMap.FRONT_LEFT_MOTOR_ID);
  public WPI_TalonFX backLeft = new WPI_TalonFX(RobotMap.BACK_LEFT_MOTOR_ID);

  public DifferentialDrive motors = new DifferentialDrive(frontLeft, frontRight);

  /** Creates a new DriveTrian. */
  public DriveTrain() {
    configMotors();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setLeftMotors(double speed) {
    frontLeft.set(ControlMode.PercentOutput, speed);
    backLeft.set(ControlMode.PercentOutput, speed);

  }

  public void setRightMotors(double speed) {
    frontRight.setSensorPhase(true);
    backRight.set(ControlMode.PercentOutput, speed);
    frontRight.set(ControlMode.PercentOutput, speed);
  }

  public void turnFullLeft(double speed) {
    setRightMotors(speed);
    setLeftMotors(-speed);
  }

  public void turnFullRight(double speed) {
    setRightMotors(-speed);
    setLeftMotors(speed);
  }

  public void changeMode() {
    frontRight.setSelectedSensorPosition(0);
    frontLeft.setSelectedSensorPosition(0);
  }

  public double encoderDegrees() {
    return frontRight.getSelectedSensorPosition();
  }

  public String allEncoder() {
    return "" + (-frontRight.getSelectedSensorPosition()) + " "
        + (-frontLeft.getSelectedSensorPosition() / RobotMap.UNITS_PER_REVOLUTION) + " "
        + (-backLeft.getSelectedSensorPosition() / RobotMap.UNITS_PER_REVOLUTION) + " "
        + (-backRight.getSelectedSensorPosition() / RobotMap.UNITS_PER_REVOLUTION);
  }

  public void configMotors() {
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);

    frontLeft.setInverted(TalonFXInvertType.CounterClockwise);
    frontRight.setInverted(TalonFXInvertType.Clockwise);
    backLeft.setInverted(TalonFXInvertType.CounterClockwise);
    backRight.setInverted(TalonFXInvertType.Clockwise);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }

}
