package frc.robot.commands;

import javax.xml.namespace.QName;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class auto_newTeleClimber extends CommandBase {
    boolean forwardOpen = false;

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        if (Robot.m_oi.leftDriverBumperPressed()) {
            Robot.climber.setClimberMotors(RobotMap.CLIMBER_TELEOP_SPEED);
        } else if (Robot.m_oi.rightDriverBumperPressed()) {
            if (!forwardOpen) {
                if (Robot.climber.getLeftEncoder() >= RobotMap.UNEXTENDED_LEFT_CLIMBER_MAX) {
                    Robot.climber.setLeftClimber(-RobotMap.CLIMBER_TELEOP_SPEED);
                } else {
                    Robot.climber.setLeftClimber(0);
                }
                if (Robot.climber.getRightEncoder() <= RobotMap.UNEXTENDED_RIGHT_CLIMBER_MAX) {
                    Robot.climber.setRightClimber(RobotMap.CLIMBER_TELEOP_SPEED);
                } else {
                    Robot.climber.setRightClimber(0);
                }
            } else {
                if (Robot.climber.getLeftEncoder() >= RobotMap.EXTENDED_LEFT_CLIMBER_MAX) {
                    Robot.climber.setLeftClimber(-RobotMap.CLIMBER_TELEOP_SPEED);
                } else {
                    Robot.climber.setLeftClimber(0);
                }

                if (Robot.climber.getRightEncoder() <= RobotMap.EXTENDED_RIGHT_CLIMBER_MAX) {
                    Robot.climber.setRightClimber(RobotMap.CLIMBER_TELEOP_SPEED);
                } else {
                    Robot.climber.setRightClimber(0);
                }

            }
        } else if (Robot.m_oi.getDriverDpad() == 0) {
            Robot.climber.setLeftClimber(-RobotMap.CLIMBER_DPAD_SPEED);
        } else if (Robot.m_oi.getDriverDpad() == 180) {
            Robot.climber.setLeftClimber(RobotMap.CLIMBER_DPAD_SPEED);
        } else if (Robot.m_oi.getDriverDpad() == 90) {
            Robot.climber.setRightClimber(RobotMap.CLIMBER_DPAD_SPEED);
        } else if (Robot.m_oi.getDriverDpad() == 270) {
            Robot.climber.setRightClimber(-RobotMap.CLIMBER_DPAD_SPEED);
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
