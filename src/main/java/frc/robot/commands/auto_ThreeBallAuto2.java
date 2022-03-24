package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_ThreeBallAuto2 extends SequentialCommandGroup {
        /**
         * Creates a new ComplexAuto.
         *
         * @param drive The drive subsystem this command will run on
         * @param hatch The hatch subsystem this command will run on
         */
        public auto_ThreeBallAuto2() {
                Robot.intake.openCompressor();
                addCommands(
                                new auto_fenderSequence(),
                                new MoveDirection(6.5),
                                new TurnDegrees(120),
                                new MoveDirection(9.25));

        }
}