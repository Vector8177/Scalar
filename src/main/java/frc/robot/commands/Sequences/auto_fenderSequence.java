package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.ShootBallRPM;
import frc.robot.commands.Teleop.teleop_ShooterNew;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_fenderSequence extends SequentialCommandGroup {
    /**
     * Creates a new ComplexAuto.
     *
     * @param drive The drive subsystem this command will run on
     * @param hatch The hatch subsystem this command will run on
     */
    public auto_fenderSequence() {
        addCommands(
                new MoveIntake(true),
                new ShootBallRPM(-1500, 2500, .5),
                new ParallelCommandGroup(
                        new ShootBallRPM(-1500, 2500, 1.5),
                        new MoveElevator(0.75, 1.5)));
    }
}