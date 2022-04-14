// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

public class DriveTrain extends SubsystemBase {
  public WPI_TalonFX frontRight = new WPI_TalonFX(RobotMap.DriveTrain.FRONT_RIGHT_MOTOR_ID);
  public WPI_TalonFX backRight = new WPI_TalonFX(RobotMap.DriveTrain.BACK_RIGHT_MOTOR_ID);
  public WPI_TalonFX frontLeft = new WPI_TalonFX(RobotMap.DriveTrain.FRONT_LEFT_MOTOR_ID);
  public WPI_TalonFX backLeft = new WPI_TalonFX(RobotMap.DriveTrain.BACK_LEFT_MOTOR_ID);

  public DifferentialDrive motors = new DifferentialDrive(frontLeft, frontRight);

  private final DifferentialDriveOdometry odometry;

  public static final AHRS ahrs = new AHRS(SPI.Port.kMXP);

  private double zeroOffset = 0;
  boolean navZeroed = false;

  /** Creates a new DriveTrian. */
  public DriveTrain() {
    configMotors();

    odometry = new DifferentialDriveOdometry(ahrs.getRotation2d());
    resetOdometry(new Pose2d());

  }

  @Override
  public void periodic() {
    if (!ahrs.isCalibrating() && !navZeroed) {
      zeroOffset = ahrs.getYaw();
      navZeroed = true;
    }

    odometry.update(new Rotation2d(Math.toRadians(-(ahrs.getYaw() - zeroOffset))),
        frontLeft.getSelectedSensorPosition() * RobotMap.DriveTrain.FT_PER_ENCODER_DEGREE,
        frontRight.getSelectedSensorPosition() * RobotMap.DriveTrain.FT_PER_ENCODER_DEGREE);

    SmartDashboard.putNumber("heading", -(ahrs.getYaw() - zeroOffset));
    SmartDashboard.putNumber("odometry x", odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("odometry y", odometry.getPoseMeters().getY());
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    motors.tankDrive(leftVolts, rightVolts);
    motors.feed();
  }

  public double getAverageEncoderDistance() {
    return (frontLeft.getSelectedSensorPosition() + frontRight.getSelectedSensorPosition()) / 2.0;
  }

  // scales maximum drive speed (0 to 1.0)
  public void setMaxOutput(double maxOutput) {
    motors.setMaxOutput(maxOutput);
  }

  // zero navx (doesn't work consistently so we avoid using)
  public void zeroHeading() {
    ahrs.reset();
  }

  // return heading in degrees (-180 to 180)
  public double getHeading() {
    return ahrs.getYaw();
    // note: getAngle returns accumulated yaw (can be <0 or >360)
    // getYaw has a 360 degree period
  }

  // return turn rate deg/sec
  public double getTurnRate() {
    // negative since navx's positive direction is opposite of the expected/wpilib
    // standard
    return -ahrs.getRate();
  }

  public void setRightMotors(double speed) {
    frontRight.setSensorPhase(true);
    backRight.set(ControlMode.PercentOutput, speed);
    frontRight.set(ControlMode.PercentOutput, speed);
  }

  public void setLeftMotors(double speed) {
    backLeft.set(ControlMode.PercentOutput, speed);
    frontLeft.set(ControlMode.PercentOutput, speed);
  }

  public void turnFullLeft(double speed) {
    setRightMotors(speed);
    setLeftMotors(-speed);
  }

  public void turnFullRight(double speed) {
    setRightMotors(-speed);
    setLeftMotors(speed);
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, new Rotation2d(-(ahrs.getYaw() - zeroOffset)));
  }

  public void resetEncoder() {
    frontRight.setSelectedSensorPosition(0);
    frontLeft.setSelectedSensorPosition(0);
  }

  public double encoderDegrees() {
    return frontRight.getSelectedSensorPosition();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        frontLeft.getSelectedSensorVelocity(),
        frontRight.getSelectedSensorVelocity());
  }

  public void resetEncoders() {
    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
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
