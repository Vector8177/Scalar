package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.commands.TurnFull;
import frc.robot.commands.TurnFullPID;
import frc.robot.commands.TurnToBall;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.MoveDirectionPID;
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

    public NetworkTableEntry bigWheelSpeed = tab.add("Big Wheel Speed", RobotMap.BIG_WHEEL_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();

    public NetworkTableEntry smallWheelSpeed = tab.add("Small Wheel Speed", RobotMap.SMALL_WHEEL_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -1))
            .getEntry();

    /*
     * == COMMANDS ==
     */
    private final Command m_DOL = new SequentialCommandGroup(new MoveDirectionPID(6.208), new TurnFullPID(122.08),
            new MoveDirectionPID(10), new TurnFullPID(-67.313), new TurnFullPID(123.187), new MoveDirectionPID(14.333),
            new TurnFullPID(-53));
    // new MoveDirection(6.208), new TurnFullPID(122.08), new MoveDirection(10.5),
    // new TurnFullPID(-67.313), new TurnFullPID(119.187), new
    // MoveDirection(15.833), new TurnFullPID(-53)
    private final Command m_TurnRight = new TurnToBall();
    private final Command m_TurnFullPID = new MoveDirectionPID(10);

    public OI() {
        m_chooser.setDefaultOption("Drive off line", m_DOL);
        m_chooser.addOption("Move forward turn right", m_TurnRight);
        m_chooser.addOption("Turn Full PID 90", m_TurnFullPID);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add("Autonomous modes", m_chooser).withSize(2, 1);
    }

    public double getTeleopSpeed() {
        return teleMaxSpeed.getDouble(RobotMap.LEFT_MOTOR_SPEED_MODIFIER);
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
     * == CONTROLLER ==
     */
    private XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER_PORT);
    private XboxController intakeController = new XboxController(RobotMap.INTAKE_CONTROLLER_PORT);

    public double getDriverRawJoystick() {
        return driverController.getLeftX();
    }

    public double getDriverRightTrigger() {
        return driverController.getRightTriggerAxis();
    }

    public double getDriverLeftTrigger() {
        return driverController.getLeftTriggerAxis();
    }

    public double getIntakeRightTrigger() {
        return intakeController.getRightTriggerAxis();
    }

    public double getIntakeLeftTrigger() {
        return intakeController.getLeftTriggerAxis();
    }

    public boolean bDriverButtonPressed() {
        return driverController.getBButton();
    }

    public boolean aDriverButtonPressed() {
        return driverController.getAButton();
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
