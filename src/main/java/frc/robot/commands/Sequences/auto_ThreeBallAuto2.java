package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.ShootBall;
import frc.robot.commands.TurnDegrees;

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
                                new ParallelCommandGroup(
                                                new MoveDirection(3.33),
                                                new IntakeBall(1.0, 1.0)),
                                new MoveElevator(-1, .15),
                                new ShootBall(-.74 * 1.2, .22, 1),
                                new ParallelCommandGroup(
                                                new ShootBall(-.74 * 1.2, .22, 1.5),
                                                new MoveElevator(.8, 1.5),
                                                new IntakeBall(1.0, 1.5)),
                                new TurnDegrees(107.67, 1, RobotMap.DriveTrain.autoPID),
                                new ParallelCommandGroup(
                                                new MoveDirection(8.7583),
                                                new IntakeBall(1.0, 2)),
                                new TurnDegrees(-67.67, 1, RobotMap.DriveTrain.autoPID),
                                new MoveDirection(-2),
                                new ShootBall(-.74 * 1.2, .22, 1),
                                new ParallelCommandGroup(
                                                new ShootBall(-.74 * 1.2, .22, 1.5),
                                                new MoveElevator(.8, 1.5),
                                                new IntakeBall(1.0, 1.0)));

        }
}