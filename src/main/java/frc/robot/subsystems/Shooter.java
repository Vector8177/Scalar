package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
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

    public void setBackMotorV(double speed) {
        backMotor.set(ControlMode.Velocity, speed);
    }

    public void setFrontMotorV(double speed) {
        frontMotor.set(ControlMode.Velocity, speed);
    }

    public double getFrontMotorVelocity() {
        return frontMotor.getSelectedSensorVelocity();
    }

    public double getBackMotorVelocity() {
        return backMotor.getSelectedSensorVelocity();
    }

    public void setMotors(double speed) {
        setFrontMotor(speed);
        setBackMotor(speed);
    }

    public double getSmallWheelPowerPV() {
        double dist = Robot.limelight.getDistance();
        return ((-.0210 * dist * dist) + (.111 * dist) + -.586);
    }

    public double getBigWheelPowerPV() {
        double dist = Robot.limelight.getDistance();
        return ((-.00647 * dist * dist) + (.0507 * dist) + .1545);
    }

    public double getSmallWheelRPM() {
        double dist = Robot.limelight.getDistance();
        return (460.41 * dist) + 574.07;
    }

    public double getBigWheelRPM() {
        double dist = Robot.limelight.getDistance();
        return (-158.26 * dist) + 2111.04;
    }

    public void motorSetup() {
        frontMotor.configFactoryDefault();
        frontMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        frontMotor.configNeutralDeadband(0.001);
        frontMotor.configNominalOutputForward(0, 0);
        frontMotor.configNominalOutputReverse(0, 0);
        frontMotor.configPeakOutputForward(1, 0);
        frontMotor.configPeakOutputReverse(-1, 0);

        frontMotor.config_kF(0, RobotMap.frontGains.kF, 0);
        frontMotor.config_kP(0, RobotMap.frontGains.kP, 0);
        frontMotor.config_kI(0, RobotMap.frontGains.kI, 0);
        frontMotor.config_kD(0, RobotMap.frontGains.kD, 0);

        backMotor.configFactoryDefault();
        backMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        backMotor.configNeutralDeadband(0.001);
        backMotor.configNominalOutputForward(0, 0);
        backMotor.configNominalOutputReverse(0, 0);
        backMotor.configPeakOutputForward(1, 0);
        backMotor.configPeakOutputReverse(-1, 0);

        backMotor.config_kF(0, RobotMap.backGains.kF, 0);
        backMotor.config_kP(0, RobotMap.backGains.kP, 0);
        backMotor.config_kI(0, RobotMap.backGains.kI, 0);
        backMotor.config_kD(0, RobotMap.backGains.kD, 0);
    }
}
