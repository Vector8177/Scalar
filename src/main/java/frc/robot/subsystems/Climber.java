package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    DoubleSolenoid climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

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

}
