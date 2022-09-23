// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Teleop.teleop_ArcadeDrive;
import frc.robot.commands.Teleop.teleop_Climber;
import frc.robot.commands.Teleop.teleop_Intake;
import frc.robot.commands.Teleop.teleop_ShooterNew;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PhotonVision;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.cameraserver.CameraServer;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  // NavX
  public static final AHRS ahrs = new AHRS(SPI.Port.kMXP);

  // Teleop Commands
  teleop_ArcadeDrive arcadeDrive = new teleop_ArcadeDrive();
  teleop_ShooterNew tShooter = new teleop_ShooterNew();
  teleop_Climber tClimber = new teleop_Climber();
  teleop_Intake tIntake = new teleop_Intake();

  // Subsystems
  public static final DriveTrain driveTrain = new DriveTrain();

  public static final PhotonVision limelight = new PhotonVision();
  public static final Intake intake = new Intake();
  public static final Climber climber = new Climber();
  public static final Shooter shooter = new Shooter();

  public static final OI m_oi = new OI();
  private Command m_autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    climber.resetEncoder();
    intake.resetEncoders();
    CameraServer.startAutomaticCapture();

    driveTrain.configMotors();

    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put ourq
    // autonomous chooser on the dashboard.

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    updateShuffleboard();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    driveTrain.resetEncoder();
    intake.resetEncoders();
    driveTrain.configMotors();
    m_autonomousCommand = m_oi.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    driveTrain.configMotors();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    driveTrain.resetEncoder();
    intake.resetEncoders();
    climber.resetEncoder();
    intake.openCompressor();

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    arcadeDrive.execute();
    tShooter.execute();
    tIntake.execute();
    tClimber.execute();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  public static void updateShuffleboard() {
    // Adds NavX values to Shuffle Board
    SmartDashboard.putNumber("GOALLL Angle", limelight.getYaw());
    SmartDashboard.putNumber("Current Angle", ahrs.getYaw());

    SmartDashboard.putNumber("Elevator Encoder",  intake.elevatorEncoderDegrees());

    SmartDashboard.putBoolean("High Beam Break", shooter.getHighBeamBreak());
    SmartDashboard.putBoolean("Low Beam Break", shooter.getLowBeamBreak());

    SmartDashboard.putNumber("Distance", limelight.getDistance());

    SmartDashboard.putNumber("Left Degrees", Robot.climber.getLeftEncoder());
    SmartDashboard.putNumber("Right Degrees", Robot.climber.getRightEncoder());

    // Adds controller values to Shuffle Board
    SmartDashboard.putNumber("DPAD", m_oi.getDriverDpad());
    SmartDashboard.putNumber("Driver Right Trigger", (double) (Math.round(m_oi.getDriverRightTrigger() * 1000)) / 1000);
    SmartDashboard.putNumber("Intake Right Trigger", (double) (Math.round(m_oi.getIntakeRightTrigger() * 1000)) / 1000);

    SmartDashboard.putNumber("Front wheel velocity", RobotMap.convertMotorVelToRPM(shooter.getFrontMotorVelocity()));
    SmartDashboard.putNumber("Back wheel velocity", RobotMap.convertMotorVelToRPM(shooter.getBackMotorVelocity()));
  }
}
