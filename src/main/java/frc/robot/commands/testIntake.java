package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class testIntake extends CommandBase{
    boolean off = false;
    private static Boolean forwardOpen = null;
    public void execute(){
        if(!off) {
        Robot.intake.setIntakeMotor(Robot.m_oi.getIntakeRightTrigger());
        }
        if(Robot.m_oi.aIntakeButtonPressed() && !off) {
            forwardOpen = true;
        }
        if(Robot.m_oi.yIntakeButtonPressed() && !off){
            forwardOpen = false;
        }
        if(forwardOpen != null && !forwardOpen && !off){
            Robot.pneu.openReverseSolenoid();
        }
        else if(forwardOpen != null && forwardOpen && !off) { 
            Robot.pneu.openForwardSolenoid(); 
        }
        if(Robot.m_oi.bIntakeButtonPressed()){
            forwardOpen = null;
            off = true;
            end();
            return;
        }
    }

    public void end(){
        Robot.intake.setIntakeMotor(0);
        Robot.pneu.closeSolenoid();
        Robot.pneu.closeCompressor();
    }
}
