package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_TwoBallAuto extends SequentialCommandGroup {
        /**
         * Creates a new ComplexAuto.
         *
         * @param drive The drive subsystem this command will run on
         * @param hatch The hatch subsystem this command will run on
         */
        public auto_TwoBallAuto() {
                Robot.intake.openCompressor();
                addCommands(
                                new ParallelCommandGroup(
                                                new MoveDirection(3.425),
                                                new IntakeBall(1.0, 1.5)),
                                new MoveDirection(-4.7),
                                new ParallelCommandGroup(
                                                new TurnDegrees(12),
                                                new ShootBall(-.55, .41, .75)),
                                new ParallelCommandGroup(
                                                new ShootBall(-.55, .41, 1.5),
                                                new IntakeBall(1, 1.5),
                                                new MoveElevator(1, 1.5)));

        }
}