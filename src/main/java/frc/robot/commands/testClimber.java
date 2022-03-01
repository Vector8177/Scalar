package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class testClimber extends CommandBase {
    boolean off = false;
    private Boolean forwardOpen = null;

    public void execute() {
        if (!off) {

        }
        if (false && Robot.m_oi.rightIntakeBumperPressed() && !off) {
            forwardOpen = true;
        }
        if (false && Robot.m_oi.leftIntakeBumperPressed() && !off) {
            forwardOpen = false;
        }
        if (forwardOpen != null && !forwardOpen && !off) {
            Robot.climber.openClimberReverseSolenoid();
        } else if (forwardOpen != null && forwardOpen && !off) {
            Robot.climber.openClimberForwardSolenoid();
        }

    }
}
