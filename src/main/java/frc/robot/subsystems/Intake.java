
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake extends SubsystemBase {
  TalonSRX elevatorSRX = new TalonSRX(7);
  TalonSRX intakeSRX = new TalonSRX(8);

  public Intake() {
  }

  @Override
  public void periodic() {
  }

  public void setElevatorMotor(double power) {
    elevatorSRX.set(TalonSRXControlMode.PercentOutput, power);
  }

  public void setIntakeMotor(double power) {
    intakeSRX.set(TalonSRXControlMode.PercentOutput, power);
  }

  Compressor compressor = new Compressor(RobotMap.Intake.PHEUMATICS_ID, PneumaticsModuleType.CTREPCM);
  DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  public double compressorReading() {
    return compressor.getPressure();
  }

  public void openIntakeForwardSolenoid() {
    solenoid.set(Value.kForward);
  }

  public void openIntakeReverseSolenoid() {
    solenoid.set(Value.kReverse);
  }

  public void closeIntakeSolenoid() {
    solenoid.set(Value.kOff);
  }

  public void closeCompressor() {
    compressor.disable();
  }

  public void openCompressor() {
    compressor.enableDigital();

  }

}