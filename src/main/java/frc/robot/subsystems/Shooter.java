package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    public TalonFX frontMotor = new TalonFX(RobotMap.Shooter.SHOOTER_FRONT_MOTOR_ID);
    public TalonFX backMotor = new TalonFX(RobotMap.Shooter.SHOOTER_BACK_MOTOR_ID);

    DigitalInput highBeam = new DigitalInput(1);
    DigitalInput lowBeam = new DigitalInput(0);

    @Override
    public void periodic() {

    }

    public void setFrontMotor(double speed) {
        frontMotor.set(ControlMode.PercentOutput, speed);
    }

    public boolean getHighBeamBreak() {
        return highBeam.get();
    }

    public boolean getLowBeamBreak() {
        return lowBeam.get();
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

    public double distToSmallWheelPower() {
        double dist = Robot.limelight.getDistance();
        return ((-.0210 * dist * dist) + (.111 * dist) + -.586);
    }

    public double distToBigWheelPower() {
        double dist = Robot.limelight.getDistance();
        return ((-.00647 * dist * dist) + (.0507 * dist) + .1545);
    }

    public double distToSmallWheelRPM() {
        double dist = Robot.limelight.getDistance();
        return Robot.m_oi.getShootSpeed() * ((-1.515 * dist * dist) + (632.92 * dist) + 191.322);
    }

    public double distToBigWheelRPM() {
        double dist = Robot.limelight.getDistance();
        return Robot.m_oi.getShootSpeed() * ((-47.74 * dist * dist) + (189.63 * dist) + 1371.24);
    }

    public void motorSetup() {
        frontMotor.configFactoryDefault();
        frontMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        frontMotor.configNeutralDeadband(0.001);
        frontMotor.configNominalOutputForward(0, 0);
        frontMotor.configNominalOutputReverse(0, 0);
        frontMotor.configPeakOutputForward(1, 0);
        frontMotor.configPeakOutputReverse(-1, 0);

        frontMotor.config_kF(0, RobotMap.Shooter.frontGains.kF, 0);
        frontMotor.config_kP(0, RobotMap.Shooter.frontGains.kP, 0);
        frontMotor.config_kI(0, RobotMap.Shooter.frontGains.kI, 0);
        frontMotor.config_kD(0, RobotMap.Shooter.frontGains.kD, 0);

        backMotor.configFactoryDefault();
        backMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        backMotor.configNeutralDeadband(0.001);
        backMotor.configNominalOutputForward(0, 0);
        backMotor.configNominalOutputReverse(0, 0);
        backMotor.configPeakOutputForward(1, 0);
        backMotor.configPeakOutputReverse(-1, 0);

        backMotor.config_kF(0, RobotMap.Shooter.backGains.kF, 0);
        backMotor.config_kP(0, RobotMap.Shooter.backGains.kP, 0);
        backMotor.config_kI(0, RobotMap.Shooter.backGains.kI, 0);
        backMotor.config_kD(0, RobotMap.Shooter.backGains.kD, 0);
    }
}
