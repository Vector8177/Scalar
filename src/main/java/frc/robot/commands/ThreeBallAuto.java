package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class ThreeBallAuto extends SequentialCommandGroup {
    /**
     * Creates a new ComplexAuto.
     *
     * @param drive The drive subsystem this command will run on
     * @param hatch The hatch subsystem this command will run on
     */
    public ThreeBallAuto() {
        addCommands(
                new ShootBall(-.375, .5, .5),
                new ParallelCommandGroup(
                        new ShootBall(-.375, .5, .5),
                        new MoveElevator(1, .5)),
                new ParallelCommandGroup(
                        new MoveDirectionPID(8.125),
                        new IntakeBall(.5, 1.8),
                        new MoveElevator(.5, 1.8)),
                new TurnFullPID(118.5),
                new ParallelCommandGroup(
                        new MoveDirectionPID(9.75),
                        new IntakeBall(.5, 2.5),
                        new MoveElevator(.5, 1)),
                new TurnFullPID(-67.5),
                new MoveDirectionPID(-6.25),
                new ParallelCommandGroup(
                        new ShootBall(-.43, .57, .75),
                        new MoveElevator(.5, -.3)),
                new ParallelCommandGroup(
                        new ShootBall(-.43, .57, 2),
                        new MoveElevator(1, 2)));
    }
}