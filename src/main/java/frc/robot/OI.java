package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.commands.TurnDegrees;
import frc.robot.commands.TurnToBall;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.auto_ThreeBallAuto;
import frc.robot.commands.IntakeBall;
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
    public NetworkTableEntry teleMaxSpeed = tab.add("Teleop Max Speed", RobotMap.DRIVE_SPEED_MODIFIER)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();
    public NetworkTableEntry autoMaxSpeed = tab.add("Autonomous Max Speed", RobotMap.AUTONOMOUS_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();

    public NetworkTableEntry bigWheelSpeed = tab.add("Big Wheel Speed", RobotMap.BIG_WHEEL_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();

    public NetworkTableEntry smallWheelSpeed = tab.add("Small Wheel Speed", RobotMap.SMALL_WHEEL_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -1))
            .getEntry();

    /*
     * == COMMANDS ==
     */

    // big : .69 small: -.51 - teleop
    private final Command m_DOL = new MoveDirection(5);
    private final Command m_TurnRight = new TurnDegrees(90);
    private final Command m_ThreeBallAuto = new auto_ThreeBallAuto();

    public OI() {
        m_chooser.setDefaultOption("Drive off line (5 ft)", m_DOL);
        m_chooser.addOption("Turn 90 degrees", m_TurnRight);
        m_chooser.addOption("Three Ball Auto", m_ThreeBallAuto);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add("Autonomous modes", m_chooser).withSize(2, 1);
    }

    public double getTeleopSpeed() {
        return teleMaxSpeed.getDouble(RobotMap.DRIVE_SPEED_MODIFIER);
    }

    public double getBigWheelSpeed() {
        return bigWheelSpeed.getDouble(RobotMap.BIG_WHEEL_SPEED);
    }

    public double getSmallWheelSpeed() {
        return smallWheelSpeed.getDouble(RobotMap.SMALL_WHEEL_SPEED);
    }

    public double getAutoSpeed() {
        return autoMaxSpeed.getDouble(RobotMap.AUTONOMOUS_SPEED);
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }

    /*
     * == CONTROLLERS ==
     */

    // Driver controller
    private XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER_PORT);

    public double getDriverRawJoystick() {
        return driverController.getLeftX();
    }

    public double getDriverRightTrigger() {
        return driverController.getRightTriggerAxis();
    }

    public double getDriverLeftTrigger() {
        return driverController.getLeftTriggerAxis();
    }

    public boolean bDriverButtonPressed() {
        return driverController.getBButton();
    }

    public boolean yDriverButtonPressed() {
        return driverController.getYButton();
    }

    public boolean aDriverButtonPressed() {
        return driverController.getAButton();
    }

    public boolean rightDriverBumperPressed() {
        return driverController.getRightBumper();
    }

    public boolean leftDriverBumperPressed() {
        return driverController.getLeftBumper();
    }

    // Intake controller
    private XboxController intakeController = new XboxController(RobotMap.INTAKE_CONTROLLER_PORT);

    public double getIntakeRightTrigger() {
        return intakeController.getRightTriggerAxis();
    }

    public double getIntakeLeftTrigger() {
        return intakeController.getLeftTriggerAxis();
    }

    public boolean bIntakeButtonPressed() {
        return intakeController.getBButton();
    }

    public boolean aIntakeButtonPressed() {
        return intakeController.getAButton();
    }

    public boolean yIntakeButtonPressed() {
        return intakeController.getYButton();
    }

    public boolean leftIntakeBumperPressed() {
        return intakeController.getLeftBumper();
    }

    public boolean rightIntakeBumperPressed() {
        return intakeController.getRightBumper();
    }

}
