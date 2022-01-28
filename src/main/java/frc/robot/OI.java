package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import frc.robot.commands.TurnFull;
import frc.robot.commands.TurnFullPID;
import frc.robot.commands.auto_PracticeAuto;
import frc.robot.commands.MoveDirection;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import java.util.Map;
import edu.wpi.first.networktables.NetworkTableEntry;

public class OI {
    /*
     * == SMARTDASHBOARD / SHUFFLEBOARD ==
     */
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    private static ShuffleboardTab tab = Shuffleboard.getTab("Speed");
    public NetworkTableEntry teleMaxSpeed = tab.add("Teleop Max Speed", RobotMap.LEFT_MOTOR_SPEED_MODIFIER)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();
    public NetworkTableEntry autoMaxSpeed = tab.add("Autonomous Max Speed", RobotMap.AUTONOMOUS_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();

    /*
     * == COMMANDS ==
     */
    private final Command m_DOL = new MoveDirection(-4);
    private final Command m_TurnRight = new SequentialCommandGroup(new MoveDirection(2), new TurnFull(90));
    private final Command m_PracticeAuto = new auto_PracticeAuto();
    private final Command m_TurnFullPID = new TurnFullPID(90);

    public OI() {
        m_chooser.setDefaultOption("Drive off line", m_DOL);
        m_chooser.addOption("Move forward turn right", m_TurnRight);
        m_chooser.addOption("Practice Autonomous", m_PracticeAuto);
        m_chooser.addOption("Turn Full PID 90", m_TurnFullPID);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add("Autonomous modes", m_chooser).withSize(2, 1);
    }

    public double getTeleopSpeed() {
        return teleMaxSpeed.getDouble(RobotMap.LEFT_MOTOR_SPEED_MODIFIER);
    }

    public double getAutoSpeed() {
        return autoMaxSpeed.getDouble(RobotMap.AUTONOMOUS_SPEED);
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }

    /*
     * == CONTROLLER ==
     */
    private XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER_PORT);

    public double getDriverRawJoystick(int axis) {
        return driverController.getLeftX();
    }

    public double getDriverRightTrigger() {
        return driverController.getRightTriggerAxis();
    }

    public double getDriverLeftTrigger() {
        return driverController.getLeftTriggerAxis();
    }

    public boolean bButtonPressed() {
        return driverController.getBButton();
    }
}
