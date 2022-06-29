package frc.robot.commands.Sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.ShootBallRPM;
import frc.robot.commands.TurnDegrees;
import frc.robot.commands.Teleop.teleop_ShooterNew;
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
                                new MoveIntake(true),
                                new TurnDegrees(Robot.limelight.getYaw(), .5,
                                                RobotMap.DriveTrain.tarmacPID),
                                new ShootBallRPM(-Robot.shooter.distToSmallWheelRPM(),
                                                Robot.shooter.distToBigWheelRPM(), .6),
                                new ParallelCommandGroup(
                                                new ShootBallRPM(-Robot.shooter.distToSmallWheelRPM(),
                                                                Robot.shooter.distToBigWheelRPM(), 2),
                                                new MoveElevator(.8, 1.5)));
                teleop_ShooterNew.resetValues(false);

        }
}