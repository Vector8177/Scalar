package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_ThreeBallAuto extends SequentialCommandGroup {
        /**
         * Creates a new ComplexAuto.
         *
         * @param drive The drive subsystem this command will run on
         * @param hatch The hatch subsystem this command will run on
         */
        public auto_ThreeBallAuto() {
                Robot.intake.openCompressor();
                addCommands(
                                new ParallelCommandGroup(
                                                new MoveDirection(3.425),
                                                new IntakeBall(1.0, 1.0)),
                                new MoveDirection(-4.905),
                                new MoveIntake(false),
                                new TurnDegrees(30),
                                new ParallelCommandGroup(
                                                new ShootBall(-.35, .37, 1.25)),
                                new MoveElevator(-.5, .2),
                                new ParallelCommandGroup(
                                                new ShootBall(-.35, .37, 1.5),
                                                new IntakeBall(1, 1.5),
                                                new MoveElevator(1, 1.5)),
                                new TurnDegrees(44),
                                new ParallelCommandGroup(
                                                new MoveDirection(7.825),
                                                new MoveElevator(1, 2),
                                                new IntakeBall(1, 2.5)),
                                new MoveDirection(-7.825),
                                new TurnDegrees(-47),
                                new MoveElevator(-.5, .2),
                                new ShootBall(-.45, .37, 1.25),
                                new ParallelCommandGroup(
                                                new ShootBall(-.35, .37, 1.5),
                                                new IntakeBall(1, 1.5),
                                                new MoveElevator(1, 1.5)));

        }
}