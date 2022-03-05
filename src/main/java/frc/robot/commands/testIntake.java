package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class testIntake extends CommandBase {
    private boolean forwardOpen = true;

    @Override
    public void execute() {
        if (Robot.m_oi.aIntakeButtonPressed()) {
            forwardOpen = true;
        }
        if (Robot.m_oi.yIntakeButtonPressed()) {
            forwardOpen = false;
        }
        if (forwardOpen) {
            Robot.intake.openIntakeForwardSolenoid();
        } else {
            Robot.intake.openIntakeReverseSolenoid();
        }

    }
}
