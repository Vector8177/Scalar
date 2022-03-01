package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class testClimber extends CommandBase {
    private boolean retract = false;
    private Boolean forwardOpen = null;

    @Override
    public void execute() {
        if (Robot.m_oi.leftDriverBumperPressed()) {
            Robot.climber.setClimberMotors(.25);
        }
        if (Robot.m_oi.rightDriverBumperPressed() && retract) {
            forwardOpen = true;
        }
        if (Robot.m_oi.rightDriverBumperPressed() && !retract) {
            forwardOpen = false;
        }
        if (forwardOpen != null && !forwardOpen && retract) {
            Robot.climber.openClimberReverseSolenoid();
        } else if (forwardOpen != null && forwardOpen && !retract) {
            Robot.climber.openClimberForwardSolenoid();
        }

    }
}
