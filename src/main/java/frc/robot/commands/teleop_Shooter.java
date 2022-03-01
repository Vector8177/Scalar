package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class teleop_Shooter extends CommandBase {
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double rightTrigger = Robot.m_oi.getIntakeRightTrigger();
        double leftTrigger = -Robot.m_oi.getIntakeLeftTrigger();
        boolean rightBumper = Robot.m_oi.rightIntakeBumperPressed();
        boolean bButton = Robot.m_oi.bIntakeButtonPressed();

        Robot.intake.setElevatorMotor(leftTrigger + rightTrigger);
        Robot.intake.setIntakeMotor(leftTrigger + rightTrigger);

        if (bButton) {
            Robot.intake.setIntakeMotor(1);
        }

        if (rightBumper) {
            Robot.shooter.setBackMotor(Robot.m_oi.getSmallWheelSpeed());
            Robot.shooter.setFrontMotor(Robot.m_oi.getBigWheelSpeed());
        } else {
            Robot.shooter.setBackMotor(0);
            Robot.shooter.setFrontMotor(0);
        }
    }
}
