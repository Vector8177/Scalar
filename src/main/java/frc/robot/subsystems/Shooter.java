package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    public TalonFX frontMotor = new TalonFX(RobotMap.SHOOTER_FRONT_MOTOR_ID);
    public TalonFX backMotor = new TalonFX(RobotMap.SHOOTER_BACK_MOTOR_ID);

    @Override
    public void periodic() {

    }

    public void setFrontMotor(double speed) {
        frontMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setBackMotor(double speed) {
        backMotor.set(ControlMode.PercentOutput, speed);
    }

    public double getFrontMotorVelocity(){
        return frontMotor.getSelectedSensorVelocity();
    }

    public double getBackMotorVelocity(){
        return backMotor.getSelectedSensorVelocity();
    }

    public void setMotors(double speed) {
        setFrontMotor(speed);
        setBackMotor(speed);
    }

    public double getSmallWheelPowerPV() {
        double dist = Robot.limelight.getDistance();
        return ((-.0210 * dist * dist) + (.121 * dist) + -.566);
    }

    public double getBigWheelPowerPV() {
        double dist = Robot.limelight.getDistance();
        return ((-.00647 * dist * dist) + (.0507 * dist) + .1545);
    }
}
