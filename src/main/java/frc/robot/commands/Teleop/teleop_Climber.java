package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class teleop_Climber extends CommandBase {
    boolean forwardOpen = false;

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        if (Robot.m_oi.leftDriverBumperPressed()) {
            Robot.climber.setClimberMotors(RobotMap.Climber.CLIMBER_TELEOP_DOWN_SPEED);
        } else if (Robot.m_oi.rightDriverBumperPressed()) {
            if (!forwardOpen) {
                if (Robot.climber.getLeftEncoder() >= RobotMap.Climber.UNEXTENDED_LEFT_CLIMBER_MAX) {
                    Robot.climber.setLeftClimber(-RobotMap.Climber.CLIMBER_TELEOP_UP_SPEED);
                } else {
                    Robot.climber.setLeftClimber(0);
                }
                if (Robot.climber.getRightEncoder() <= RobotMap.Climber.UNEXTENDED_RIGHT_CLIMBER_MAX) {
                    Robot.climber.setRightClimber(RobotMap.Climber.CLIMBER_TELEOP_UP_SPEED);
                } else {
                    Robot.climber.setRightClimber(0);
                }
            } else {
                if (Robot.climber.getLeftEncoder() >= RobotMap.Climber.EXTENDED_LEFT_CLIMBER_MAX) {
                    Robot.climber.setLeftClimber(-RobotMap.Climber.CLIMBER_TELEOP_UP_SPEED);
                } else {
                    Robot.climber.setLeftClimber(0);
                }

                if (Robot.climber.getRightEncoder() <= RobotMap.Climber.EXTENDED_RIGHT_CLIMBER_MAX) {
                    Robot.climber.setRightClimber(RobotMap.Climber.CLIMBER_TELEOP_UP_SPEED);
                } else {
                    Robot.climber.setRightClimber(0);
                }

            }
        } else if (Robot.m_oi.getDriverDpad() == 0) {
            Robot.climber.setLeftClimber(-RobotMap.Climber.CLIMBER_DPAD_SPEED);
        } else if (Robot.m_oi.getDriverDpad() == 180) {
            Robot.climber.setLeftClimber(RobotMap.Climber.CLIMBER_DPAD_SPEED);
        } else if (Robot.m_oi.getDriverDpad() == 90) {
            Robot.climber.setRightClimber(RobotMap.Climber.CLIMBER_DPAD_SPEED);
        } else if (Robot.m_oi.getDriverDpad() == 270) {
            Robot.climber.setRightClimber(-RobotMap.Climber.CLIMBER_DPAD_SPEED);
        } else { // retracted is positive
            Robot.climber.setClimberMotors(0);
        }

        if (Robot.m_oi.yDriverButtonPressed()) { // extends
            forwardOpen = true;
        }
        if (Robot.m_oi.aDriverButtonPressed()) {
            forwardOpen = false;
        }
        if (forwardOpen) {
            Robot.climber.openClimberForwardSolenoid();
        } else {
            Robot.climber.openClimberReverseSolenoid();
        }
    }
}
