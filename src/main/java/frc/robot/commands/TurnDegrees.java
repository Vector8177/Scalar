// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Utility.Gains;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnDegrees extends CommandBase {
    private final double degrees;
    private double pidcalc;
    private double duration;
    private Gains pidValues;
    PIDController pid;
    Timer time = new Timer();

    /** Creates a new ArcadeDrive. */
    public TurnDegrees(double degrees, double duration, Gains pidValues) {
        this.degrees = degrees;
        this.duration = duration;
        this.pidValues = pidValues;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        time.start();
        time.reset();
        Robot.ahrs.zeroYaw();
        pid = new PIDController(pidValues.kP, pidValues.kI, pidValues.kD);
        pid.setTolerance(.5);
        pid.setIntegratorRange(0, 10);
        pid.enableContinuousInput(-180, 180);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        float yaw = Robot.ahrs.getYaw();
        pidcalc = MathUtil.clamp(pid.calculate(yaw, degrees), -Robot.m_oi.getAutoSpeed(),
                Robot.m_oi.getAutoSpeed());
        SmartDashboard.putNumber("Turning PID Output", pidcalc);
        SmartDashboard.putData("Turning PID", pid);
        SmartDashboard.putNumber("Goal Angle", degrees);

        Robot.driveTrain.motors.tankDrive(pidcalc, -pidcalc);
    }

    public double getPIDOutput() {
        return pidcalc;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.driveTrain.motors.tankDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (duration != 0) {
            if (pid.atSetpoint() || time.get() > duration) {

                Robot.driveTrain.resetEncoder();
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
