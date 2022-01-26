// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.teleop_ArcadeDrive;
import frc.robot.commands.auto_DriveOffLine;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  public TalonFX frontRight = new TalonFX(RobotMap.FRONT_RIGHT_MOTOR_ID);
  public TalonFX backRight = new TalonFX(RobotMap.BACK_RIGHT_MOTOR_ID);
  public TalonFX frontLeft = new TalonFX(RobotMap.FRONT_LEFT_MOTOR_ID);
  public TalonFX backLeft = new TalonFX(RobotMap.BACK_LEFT_MOTOR_ID);

  /** Creates a new DriveTrian. */
  public DriveTrain() {
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
    frontRight.set(ControlMode.PercentOutput, -speed);
    backRight.set(ControlMode.PercentOutput, -speed);
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

  }

  public double encoderDegrees() {
    return (-frontRight.getSelectedSensorPosition() / RobotMap.UNITS_PER_REVOLUTION);
  }

  public void setAutoMotorsForward(double feet) {
    while (Math.abs(encoderDegrees()) <= (feet / RobotMap.DISTANCE_PER_REVOLUTION_FT)) {
      setLeftMotors(RobotMap.AUTONOMOUS_SPEED);
      setRightMotors(RobotMap.AUTONOMOUS_SPEED);
    }
    System.out.println("Motor Position: " + -frontRight.getSelectedSensorPosition());
  }

  public void setAutoMotorsBackward(double feet) {
    while (Math.abs(encoderDegrees()) >= -(feet / RobotMap.DISTANCE_PER_REVOLUTION_FT)) {
      setLeftMotors(-RobotMap.AUTONOMOUS_SPEED);
      setRightMotors(-RobotMap.AUTONOMOUS_SPEED);
    }
  }

  public void setAutoMotorsRight(double degrees) {
    System.out.println("DEGREES: " + degrees);
    System.out.println("INITIAL YAW: " + Robot.ahrs.getYaw());
    while (Robot.ahrs.getYaw() < degrees - ((int)(degrees / RobotMap.EXTRA_DEGREES_PER_DEGREE))) {
      setRightMotors(-RobotMap.AUTONOMOUS_SPEED);
      setLeftMotors(RobotMap.AUTONOMOUS_SPEED);
    }

    /*
     * // == EXPERIMENTAL CODE FOR DEGREE TURNING == //
     * while(Math.abs(encoderDegrees()) <=
     * (degrees/RobotMap.DEGREES_PER_REVOLUTION)){
     * Timer.delay(.005);
     * frontRight.set(ControlMode.PercentOutput, RobotMap.AUTONOMOUS_SPEED);
     * backRight.set(ControlMode.PercentOutput, RobotMap.AUTONOMOUS_SPEED);
     * frontLeft.set(ControlMode.PercentOutput, RobotMap.AUTONOMOUS_SPEED);
     * backLeft.set(ControlMode.PercentOutput, RobotMap.AUTONOMOUS_SPEED);
     * }
     */
    /*
     * while(Robot.ahrs.getYaw() != 90){
     * if(Robot.ahrs.getYaw() > 90){
     * turnFullLeft(RobotMap.CORRECTION_SPEED);
     * }
     * if(Robot.ahrs.getYaw() < 90){
     * turnFullRight(RobotMap.CORRECTION_SPEED);
     * }
     * }
     */
  }

  public void setAutoMotorsLeft(double degrees) {
    System.out.println("DEGREES: " + degrees);
    System.out.println("INITIAL YAW: " + Robot.ahrs.getYaw());
    while (Robot.ahrs.getYaw() - (95 * RobotMap.AUTONOMOUS_SPEED) > -degrees) {
      setRightMotors(RobotMap.AUTONOMOUS_SPEED);
      setLeftMotors(-RobotMap.AUTONOMOUS_SPEED);
    }
  }
}