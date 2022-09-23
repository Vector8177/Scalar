package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Robot;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveElevatorEnc;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.Sequences.auto_customSequence;
import frc.robot.commands.Sequences.auto_fenderSequence;
import frc.robot.commands.Sequences.auto_tarmacSequence;

public class teleop_ShooterNew extends CommandBase {

    // BOOLEAN VARIABLES
    boolean xPressed = false;
    boolean highBeamBreak = false;
    boolean lowBeamBreak = false;
    static boolean ballStored = false;
    static boolean twoBalls = false;
    static boolean oneBall = false;
    int timer = 0;

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        timer++;
        // CONTROLLER VALUES
        double rightTrigger = Robot.m_oi.getIntakeRightTrigger();
        double leftTrigger = Robot.m_oi.getIntakeLeftTrigger();
        boolean rightBumper = Robot.m_oi.rightIntakeBumperPressed();
        boolean leftBumper = Robot.m_oi.leftIntakeBumperPressed();
        boolean bButton = Robot.m_oi.bIntakeButtonPressed();
        boolean xButton = Robot.m_oi.xIntakeButtonPressed();

        // ELEVATOR AUTO-SENSING
        if (!oneBall && !Robot.shooter.getLowBeamBreak()) {
            oneBall = true;
            timer = 0;
        }
        if (timer > 10 && oneBall && !Robot.shooter.getLowBeamBreak() && !Robot.shooter.getHighBeamBreak()) {
            oneBall = false;
            twoBalls = true;
        }

        double elevatorPower = rightTrigger - leftTrigger;
        if (elevatorPower > 0 && !twoBalls) {
            Robot.intake.setElevatorMotor(-elevatorPower *.6);
        } else if (elevatorPower < 0) {
            Robot.intake.setElevatorMotor(-elevatorPower *.6);
        } else {
            Robot.intake.setElevatorMotor(0);
            if (!ballStored && twoBalls) {
                CommandScheduler.getInstance().cancelAll();
                new MoveIntake(true).schedule();
                new MoveElevator(-0.5, 0.25).schedule();
                timer = 0;
                ballStored = true;
            }
        }
        Robot.intake.setIntakeMotor(elevatorPower);

        if (twoBalls) {
            if (Robot.shooter.getLowBeamBreak() && Robot.shooter.getHighBeamBreak() && timer > 250) {
                teleop_ShooterNew.resetValues(false);
            }
        }

        // EJECTION CODE
        if (bButton) {
            new IntakeBall(1, .4).schedule();
            new MoveElevator(-1, .4).schedule();
        }

        // SHOOTING SEQUENCES
        if (rightBumper) {
            new auto_fenderSequence().schedule();
            teleop_ShooterNew.resetValues(false);
        }
        if (leftBumper) {
            new auto_tarmacSequence().schedule();
            teleop_ShooterNew.resetValues(false);
        }
        if (xButton && !xPressed) {
            new auto_customSequence().schedule();
            xPressed = true;
        } else {
            xPressed = false;
        }
    }

    public static void resetValues(boolean ball) {
        oneBall = ball;
        twoBalls = ball;
        ballStored = ball;
    }
}
