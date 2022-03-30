package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
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
                                                new MoveElevator(-1, .25)),
                                new ShootBall(Robot.shooter.getSmallWheelPowerPV() * 1.2,
                                                Robot.shooter.getBigWheelPowerPV(), .5),
                                new ParallelCommandGroup(
                                                new ShootBall(Robot.shooter.getSmallWheelPowerPV(),
                                                                Robot.shooter.getBigWheelPowerPV(), 2),
                                                new MoveElevator(.75, 2)));

        }
}