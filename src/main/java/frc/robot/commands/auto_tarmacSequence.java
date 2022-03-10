package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
                new MoveElevator(-1, .2),
                new ShootBall(-.67, .48, .3),
                new ParallelCommandGroup(
                        new ShootBall(-.67, .48, 1.5),
                        new MoveElevator(1, 1.5)));

    }
}