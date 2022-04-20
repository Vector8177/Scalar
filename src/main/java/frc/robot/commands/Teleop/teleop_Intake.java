package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class teleop_Intake extends CommandBase {

    @Override
    public void execute() {
        if (Robot.m_oi.yIntakeButtonPressed()) {
            Robot.intake.forwardOpen = true;
        }
        if (Robot.m_oi.aIntakeButtonPressed()) {
            Robot.intake.forwardOpen = false;
        }

    }
}
