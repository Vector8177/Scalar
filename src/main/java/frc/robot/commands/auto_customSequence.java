package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
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
    public auto_customSequence() {
        addCommands(
                new MoveElevator(-1, .15),
                new ShootBall(Robot.m_oi.getSmallWheelSpeed() * 1.2,
                        Robot.m_oi.getBigWheelSpeed(), 1),
                new ParallelCommandGroup(
                        new ShootBall(Robot.m_oi.getSmallWheelSpeed(),
                                Robot.m_oi.getBigWheelSpeed(), 2),
                        new MoveElevator(.8, 2)));

    }
}