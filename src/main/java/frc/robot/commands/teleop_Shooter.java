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
        boolean leftBumper = Robot.m_oi.leftIntakeBumperPressed();
        boolean bButton = Robot.m_oi.bIntakeButtonPressed();

        Robot.intake.setElevatorMotor(leftTrigger + rightTrigger);
        Robot.intake.setIntakeMotor(leftTrigger + rightTrigger);

        if (bButton) {
            Robot.intake.setIntakeMotor(1);
        }

        if (rightBumper) {
            new auto_FenderSequence().schedule();
        }
        if (leftBumper) {
            new auto_TarmacSequence().schedule();
        }

        if (Robot.m_oi.aDriverButtonPressed()) {
            // Set shooter motors based on limelight reading and calculation based on
            // distance from reflective tape)
            // Ex. Robot.shooter.setMotors(Robot.limelight.getReflectDistance() *
            // RobotMap.DISTANCE_CALCULATOR)
        }
    }
}
