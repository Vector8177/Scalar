package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.ShootBall;
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
                                                new MoveDirection(4.25),
                                                new IntakeBall(1.0, 1.75)),
                                new MoveIntake(false),
                                new MoveDirection(-5.95),
                                new MoveElevator(-1, .15),
                                new ShootBall(-.30, .39, 1),
                                new ParallelCommandGroup(
                                                new ShootBall(-.30, .39, 1),
                                                new MoveElevator(1, .25)),
                                new ParallelCommandGroup(
                                                new ShootBall(-.30, .39, 1.5),
                                                new MoveElevator(1, 1.5)));

        }
}