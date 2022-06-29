package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.commands.TurnDegrees;
import frc.robot.commands.MoveDirection;
import frc.robot.commands.PlayMusic;
import frc.robot.commands.Sequences.auto_FourBallAuto;
import frc.robot.commands.Sequences.auto_OneBallAuto;
import frc.robot.commands.Sequences.auto_ThreeBallAuto;
import frc.robot.commands.Sequences.auto_TwoBallAuto;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.Map;
import edu.wpi.first.networktables.NetworkTableEntry;

public class OI {
    /*
     * == SMARTDASHBOARD / SHUFFLEBOARD ==
     */

    SendableChooser<Command> m_chooser = new SendableChooser<>();

    private static ShuffleboardTab tab = Shuffleboard.getTab("Speed");
    NetworkTableEntry teleMaxSpeed = tab.add("Teleop Max Speed", RobotMap.DriveTrain.DRIVE_SPEED_MODIFIER)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();
    NetworkTableEntry autoMaxSpeed = tab.add("Autonomous Max Speed", RobotMap.DriveTrain.AUTONOMOUS_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0))
            .getEntry();

    NetworkTableEntry bigWheelSpeed = tab.add("Big Wheel RPM", RobotMap.Shooter.BIG_WHEEL_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kTextView)
            .getEntry();

    NetworkTableEntry smallWheelSpeed = tab.add("Small Wheel RPM", RobotMap.Shooter.SMALL_WHEEL_SPEED)
            .withSize(2, 1).withWidget(BuiltInWidgets.kTextView)
            .getEntry();
    NetworkTableEntry shooterMultiplier = tab.add("Shooter Multiplier", RobotMap.Shooter.SHOOT_SPEED_MODIFIER)
            .withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", .6))
            .getEntry();

    /*
     * == COMMANDS ==
     */
    private final Command m_DOL = new MoveDirection(5);
    private final Command m_TurnRight = new TurnDegrees(90, 1, RobotMap.DriveTrain.autoPID);
    private final Command m_OneBallAuto = new auto_OneBallAuto();
    private final Command m_ThreeBallAuto = new auto_ThreeBallAuto();
    private final Command m_TwoBallAuto = new auto_TwoBallAuto();
    private final Command m_FourBallAuto = new auto_FourBallAuto();
    private final Command m_PlayGummy = new PlayMusic("gummy.chrp");

    public OI() {
        m_chooser.addOption("Drive Off Line (5 ft)", m_DOL);
        m_chooser.addOption("Turn 90 Degrees", m_TurnRight);
        m_chooser.setDefaultOption("Three Ball Auto", m_ThreeBallAuto);
        m_chooser.addOption("Four Ball Auto", m_FourBallAuto);
        m_chooser.addOption("Two Ball Auto", m_TwoBallAuto);
        m_chooser.addOption("One Ball Auto", m_OneBallAuto);
        m_chooser.addOption("Play Gummy Bear Song", m_PlayGummy);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add("Autonomous modes", m_chooser).withSize(2, 1);
    }

    // SHUFFLEBOARD SPEED TAB
    public double getTeleopSpeed() {
        return teleMaxSpeed.getDouble(RobotMap.DriveTrain.DRIVE_SPEED_MODIFIER);
    }

    public double getBigWheelSpeed() {
        return bigWheelSpeed.getDouble(RobotMap.Shooter.BIG_WHEEL_SPEED);
    }

    public double getSmallWheelSpeed() {
        return smallWheelSpeed.getDouble(RobotMap.Shooter.SMALL_WHEEL_SPEED);
    }

    public double getAutoSpeed() {
        return autoMaxSpeed.getDouble(RobotMap.DriveTrain.AUTONOMOUS_SPEED);
    }

    public double getShootSpeed() {
        return shooterMultiplier.getDouble(RobotMap.Shooter.SHOOT_SPEED_MODIFIER);
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }

    /*
     * == CONTROLLERS ==
     */

    // Driver controller
    private XboxController driverController = new XboxController(RobotMap.Controller.DRIVER_CONTROLLER_PORT);

    public double getDriverRawJoystick() {
        return driverController.getLeftX();
    }

    public double getDriverRightTrigger() {
        return driverController.getRightTriggerAxis();
    }

    public double getDriverLeftTrigger() {
        return driverController.getLeftTriggerAxis();
    }

    public double getDriverDpad() {
        return driverController.getPOV();
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
    private XboxController intakeController = new XboxController(RobotMap.Controller.INTAKE_CONTROLLER_PORT);

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

    public boolean xIntakeButtonPressed() {
        return intakeController.getXButton();
    }

}
