package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class testClimber extends CommandBase {
    private boolean forwardOpen = false;

    @Override
    public void execute() {
        /*
         * if (Robot.m_oi.leftDriverBumperPressed()) {
         * Robot.climber.setClimberMotors(.5);
         * } else if (Robot.m_oi.rightDriverBumperPressed()) {
         * Robot.climber.setClimberMotors(-.5);
         * } else {
         * Robot.climber.setClimberMotors(0);
         * }
         * 
         * if (Robot.m_oi.yDriverButtonPressed()) {
         * forwardOpen = true;
         * }
         * if (Robot.m_oi.aDriverButtonPressed()) {
         * forwardOpen = false;
         * }
         * if (forwardOpen) {
         * Robot.climber.openClimberForwardSolenoid();
         * } else {
         * Robot.climber.openClimberReverseSolenoid();
         * }
         */
    }
}
