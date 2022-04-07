package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.ShootBallRPM;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_customSequence extends SequentialCommandGroup {
        /**
         * Creates a new ComplexAuto.
         *
         * @param drive The drive subsystem this command will run on
         * @param hatch The hatch subsystem this command will run on
         */
        // Small wheel = Back wheel = negative
        // big wheel = front wheel = positive
        public auto_customSequence() {
                addCommands(
                                new MoveElevator(-1, .1),
                                new ShootBallRPM(-Robot.m_oi.getSmallWheelSpeed(),
                                                Robot.m_oi.getBigWheelSpeed(), .6),
                                new ParallelCommandGroup(
                                                new ShootBallRPM(-Robot.m_oi.getSmallWheelSpeed(),
                                                                Robot.m_oi.getBigWheelSpeed(), 2),
                                                new MoveElevator(.65, 2)));

        }
}