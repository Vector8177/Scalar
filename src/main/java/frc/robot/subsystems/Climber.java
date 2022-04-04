package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    DoubleSolenoid climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    TalonFX leftClimber = new TalonFX(10);
    TalonFX rightClimber = new TalonFX(11);

    public Climber() {
        climberSolenoid.set(Value.kOff);
    }

    public void openClimberForwardSolenoid() {
        climberSolenoid.set(Value.kForward);
    }

    public void openClimberReverseSolenoid() {
        climberSolenoid.set(Value.kReverse);
    }

    public void closeClimberSolenoid() {
        climberSolenoid.set(Value.kOff);
    }

    public void changeMode() {
        leftClimber.setSelectedSensorPosition(0);
        rightClimber.setSelectedSensorPosition(0);

    }

    public void setClimberMotors(double speed) {
        leftClimber.set(ControlMode.PercentOutput, speed);
        rightClimber.set(ControlMode.PercentOutput, -speed);
    }

    public void setLeftClimber(double speed) {
        leftClimber.set(ControlMode.PercentOutput, speed);
    }

    public void setRightClimber(double speed) {
        rightClimber.set(ControlMode.PercentOutput, speed);
    }

    public double getLeftEncoder() {
        return leftClimber.getSelectedSensorPosition();
    }

    public double getRightEncoder() {
        return rightClimber.getSelectedSensorPosition();
    }

}
