package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.ShootBallRPM;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_OneBallAuto extends SequentialCommandGroup {
        /**
         * Creates a new ComplexAuto.
         *
         * @param drive The drive subsystem this command will run on
         * @param hatch The hatch subsystem this command will run on
         */
        public auto_OneBallAuto() {
                addCommands(
                                new ShootBallRPM(-2250, 1400, .3),
                                new ParallelCommandGroup(
                                                new ShootBallRPM(-2250, 1400, .75),
                                                new MoveElevator(1, .75)),
                                new MoveDirection(7));

        }
}