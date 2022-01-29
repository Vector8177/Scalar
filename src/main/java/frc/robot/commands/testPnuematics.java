package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class testPnuematics extends CommandBase {

    public testPnuematics() {
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.pneu.openCompressor();
        Robot.pneu.openForwardSolenoid();
        Robot.pneu.openReverseSolenoid();
        Timer.delay(5);
        end(true);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.pneu.closeSolenoid();
        Robot.pneu.closeCompressor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
