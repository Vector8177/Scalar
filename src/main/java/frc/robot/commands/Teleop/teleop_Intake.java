package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class teleop_Intake extends CommandBase {
    private boolean forwardOpen = true;

    @Override
    public void execute() {
        if (Robot.m_oi.yIntakeButtonPressed()) {
            forwardOpen = true;
        }
        if (Robot.m_oi.aIntakeButtonPressed()) {
            forwardOpen = false;
        }
        if (forwardOpen) {
            Robot.intake.openIntakeForwardSolenoid();
        } else {
            Robot.intake.openIntakeReverseSolenoid();
        }

    }
}
