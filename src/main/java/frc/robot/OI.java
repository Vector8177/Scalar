package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import frc.robot.commands.auto_DriveOffLine;
import frc.robot.commands.auto_TurnRight;
import frc.robot.commands.TurnFull;
import frc.robot.commands.MoveDirection;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class OI {
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    private XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER_PORT);
    private final Command m_DOL = new auto_TurnRight();
    private final Command m_TurnRight = new SequentialCommandGroup(new MoveDirection(2), new TurnFull(90));
    private final Command m_PracticeAuto = new SequentialCommandGroup(new MoveDirection(1), new TurnFull(90), new MoveDirection(1), new TurnFull(-90));

    public OI() {
        m_chooser.setDefaultOption("Drive off line", m_DOL);
        m_chooser.addOption("Move forward turn right", m_TurnRight);
        m_chooser.addOption("Practice Autonomous", m_PracticeAuto);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add(m_chooser);
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }

    public double GetDriverRawJoystick(int axis) {
        return driverController.getLeftX();
    }

    public double GetDriverRightTrigger() {
        return driverController.getRightTriggerAxis();
    }

    public double GetDriverLeftTrigger() {
        return driverController.getLeftTriggerAxis();
    }

}
