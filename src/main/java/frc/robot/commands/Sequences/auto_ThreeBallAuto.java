package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.ShootBallRPM;
import frc.robot.commands.TurnDegrees;
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
                                                new MoveDirection(3.125),
                                                new IntakeBall(1.0, 1.0)),
                                new MoveDirection(-4.605),
                                new MoveIntake(true),
                                new TurnDegrees(30, 1, RobotMap.DriveTrain.autoPID),
                                new MoveElevator(-.5, .2),
                                new ShootBallRPM(-1500, 2500, .5),
                                new ParallelCommandGroup(
                                                new ShootBallRPM(-1500, 2500, 1.5),
                                                new IntakeBall(1, 1.5),
                                                new MoveElevator(1, 1.5)),
                                new TurnDegrees(48, 1, RobotMap.DriveTrain.autoPID),
                                new ParallelCommandGroup(
                                                new MoveDirection(7.825),
                                                new MoveElevator(1, 2),
                                                new IntakeBall(1, 2.25)),
                                new MoveDirection(-7.525),
                                new TurnDegrees(-51, 1, RobotMap.DriveTrain.autoPID),
                                new MoveElevator(-.5, .2),
                                new ShootBallRPM(-1500, 2400, .75),
                                new ParallelCommandGroup(
                                                new ShootBallRPM(-1500, 2400, 1.5),
                                                new IntakeBall(1, 1.5),
                                                new MoveElevator(1, 1.5)));

        }
}
