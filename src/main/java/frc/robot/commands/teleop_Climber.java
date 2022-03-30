package frc.robot.commands;

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
            Robot.climber.setClimberMotors(RobotMap.CLIMBER_TELEOP_SPEED);
        } else if (Robot.m_oi.rightDriverBumperPressed()) {
            if (!forwardOpen && (Robot.climber.getLeftEncoder() >= -211362
                    || Robot.climber.getRightEncoder() >= -216791)) { // out
                Robot.climber.setClimberMotors(-RobotMap.CLIMBER_TELEOP_SPEED);
            } else if (forwardOpen && (Robot.climber.getLeftEncoder() >= -274750
                    || Robot.climber.getRightEncoder() >= -289374)) { // out
                Robot.climber.setClimberMotors(-RobotMap.CLIMBER_TELEOP_SPEED);
            } else {
                Robot.climber.setClimberMotors(0);
            }
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
