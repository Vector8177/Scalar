package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class testIntake extends CommandBase {
    private Boolean forwardOpen = null;
    private boolean retract = false;

    @Override
    public void execute() {
        if (Robot.m_oi.aIntakeButtonPressed() && retract) {
            forwardOpen = true;
            retract = false;
        }
        if (Robot.m_oi.aIntakeButtonPressed() && !retract) {
            forwardOpen = false;
            retract = true;
        }
        if (forwardOpen != null && !forwardOpen && retract) {
            Robot.intake.openIntakeReverseSolenoid();
        } else if (forwardOpen != null && forwardOpen && !retract) {
            Robot.intake.openIntakeForwardSolenoid();
        }

    }
}
