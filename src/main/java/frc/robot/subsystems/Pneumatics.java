
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  Compressor compressor = new Compressor(RobotMap.PHEUMATICS_ID, PneumaticsModuleType.CTREPCM);
  DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

  public Pneumatics() {
    solenoid.set(Value.kOff);
  }

  @Override
  public void periodic() {

  }

  public double compressorReading() {
    return compressor.getPressure();
  }

  public void openForwardSolenoid() {
    solenoid.set(Value.kForward);
  }

  public void openReverseSolenoid() {
    solenoid.set(Value.kReverse);
  }

  public void closeSolenoid() {
    solenoid.set(Value.kOff);
  }

  public void closeCompressor() {
    compressor.disable();
  }

  public void openCompressor() {
    compressor.enableDigital();

  }

}