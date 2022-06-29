package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.Sequences.auto_customSequence;
import frc.robot.commands.Sequences.auto_fenderSequence;
import frc.robot.commands.Sequences.auto_tarmacSequence;

public class teleop_ShooterOld extends CommandBase {
    boolean xPressed = false;

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double rightTrigger = Robot.m_oi.getIntakeRightTrigger();
        double leftTrigger = Robot.m_oi.getIntakeLeftTrigger();
        boolean rightBumper = Robot.m_oi.rightIntakeBumperPressed();
        boolean leftBumper = Robot.m_oi.leftIntakeBumperPressed();
        boolean bButton = Robot.m_oi.bIntakeButtonPressed();
        boolean xButton = Robot.m_oi.xIntakeButtonPressed();

        double elevatorPower = rightTrigger - leftTrigger;
        if (elevatorPower > 0 && (Robot.shooter.getLowBeamBreak() && Robot.shooter.getHighBeamBreak())) {
            Robot.intake.setElevatorMotor(elevatorPower);
        } else if (elevatorPower < 0) {
            Robot.intake.setElevatorMotor(elevatorPower);
        } else {
            Robot.intake.setElevatorMotor(0);
        }
        Robot.intake.setIntakeMotor(elevatorPower);

        if (bButton) {
            Robot.intake.setIntakeMotor(1);
        }

        if (rightBumper) {
            new auto_fenderSequence().schedule();
        }
        if (leftBumper) {
            new auto_tarmacSequence().schedule();
        }
        if (xButton && !xPressed) {
            new auto_customSequence().schedule();
            xPressed = true;
        } else {
            xPressed = false;
        }

        if (Robot.m_oi.aDriverButtonPressed()) {
            // Set shooter motors based on limelight reading and calculation based on
            // distance from reflective tape)
            // Ex. Robot.shooter.setMotors(Robot.limelight.getReflectDistance() *
            // RobotMap.DISTANCE_CALCULATOR)
        }
    }
}
