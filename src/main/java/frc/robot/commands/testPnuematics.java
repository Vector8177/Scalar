package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Pneumatics;

public class testPnuematics extends CommandBase {
private static boolean cycleOnce = false;
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
        Robot.pneu.openReverseSolenoid();
        Timer.delay(8);
        Robot.pneu.openForwardSolenoid();
        Timer.delay(8);
        Robot.pneu.openReverseSolenoid();
        Timer.delay(8);
        Robot.pneu.openForwardSolenoid();
        Timer.delay(8);
        end(true);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        cycleOnce = true;
        Robot.pneu.closeSolenoid();
        Robot.pneu.closeCompressor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(cycleOnce)
        return true;
        return false;
    }
}
