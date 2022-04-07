package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.ShootBallRPM;
import frc.robot.commands.TurnDegrees;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_FourBallAuto extends SequentialCommandGroup {
    /**
     * Creates a new ComplexAuto.
     *
     * @param drive The drive subsystem this command will run on
     * @param hatch The hatch subsystem this command will run on
     */
    public auto_FourBallAuto() {
        Robot.intake.openCompressor();
        addCommands(
                new ParallelCommandGroup(
                        new MoveDirection(3),
                        new IntakeBall(1.0, 1.0)),
                new MoveDirection(-4.745),
                new MoveIntake(false),
                new MoveElevator(-1, .2),
                new ParallelCommandGroup(
                        new ShootBallRPM(-1500, 2400, 1.5),
                        new MoveElevator(1, 1.5)),
                new TurnDegrees(73.64),
                new ParallelCommandGroup(
                        new MoveDirection(20.8),
                        new IntakeBall(1.0, 5),
                        new MoveElevator(1.0, 2.5)),
                new MoveDirection(-20.3),
                new TurnDegrees(-73.64),
                new MoveElevator(-.8, .2),
                new MoveIntake(false),
                new ParallelCommandGroup(
                        new ShootBallRPM(-1500, 2400, 1.5),
                        new MoveElevator(1, 1.5)));

    }
}