package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.ShootBallRPM;
import frc.robot.commands.TurnDegrees;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_tarmacSequence extends SequentialCommandGroup {
        /**
         * Creates a new ComplexAuto.
         *
         * @param drive The drive subsystem this command will run on
         * @param hatch The hatch subsystem this command will run on
         */
        public auto_tarmacSequence() {
                addCommands(
                                new ParallelCommandGroup(new TurnDegrees(Robot.limelight.getYaw()),
                                                new MoveElevator(-1, .15)),
                                new ShootBallRPM(-Robot.shooter.distToSmallWheelRPM(),
                                                Robot.shooter.distToBigWheelRPM(), .5),
                                new ParallelCommandGroup(
                                                new ShootBallRPM(-Robot.shooter.distToSmallWheelRPM(),
                                                                Robot.shooter.distToBigWheelRPM(), 2),
                                                new MoveElevator(.75, 2)));

        }
}