package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;
public class OI {
    private XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER_PORT);

    public double GetDriverRawJoystick(int axis){
       return driverController.getLeftX();
    }
    public double GetDriverRightTrigger(){
        return driverController.getRightTriggerAxis();
    }
    public double GetDriverLeftTrigger(){
        return driverController.getLeftTriggerAxis();
    }

    
}
