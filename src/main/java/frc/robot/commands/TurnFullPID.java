// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnFullPID extends CommandBase {
    private final double degrees;
    private double pidcalc;
    public PIDController pid = new PIDController(.02, .001, .002);

    /** Creates a new ArcadeDrive. */
    public TurnFullPID(double degreess) {
        degrees = degreess;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.ahrs.zeroYaw();
        pid.setTolerance(1.5);
        pid.enableContinuousInput(-180, 180);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        float yaw = Robot.ahrs.getYaw();
        pidcalc = MathUtil.clamp(pid.calculate(yaw, degrees), -Robot.m_oi.getAutoSpeed(), Robot.m_oi.getAutoSpeed());
        SmartDashboard.putNumber("PID Output", pidcalc);
        SmartDashboard.putData("PID Controller", pid);
        Robot.driveTrain.setLeftMotors(pidcalc);
        Robot.driveTrain.setRightMotors(-pidcalc);
    }

    public double getPIDOutput() {
        return pidcalc;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.driveTrain.setRightMotors(0);
        Robot.driveTrain.setLeftMotors(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (pid.atSetpoint()) {

            Robot.driveTrain.changeMode();
            return true;
        }
        return false;
    }
}
