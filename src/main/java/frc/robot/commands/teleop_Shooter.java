package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class teleop_Shooter extends CommandBase{


    public void initialize() {
    }

    public void execute(){
        double rightTrigger = Robot.m_oi.getIntakeRightTrigger();
    double leftTrigger = -Robot.m_oi.getIntakeLeftTrigger();
        boolean rightBumper = Robot.m_oi.rightIntakeBumperPressed();

        Robot.intake.setElevatorMotor(leftTrigger + rightTrigger);
        Robot.intake.setIntakeMotor(leftTrigger + rightTrigger);

        if(rightBumper){
            Robot.shooter.setBackMotor(-1);
            Robot.shooter.setFrontMotor(.75);
        } else{
            Robot.shooter.setBackMotor(0);
            Robot.shooter.setFrontMotor(0);
        }
    }
}
