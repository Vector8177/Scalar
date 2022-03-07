// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.teleop_ArcadeDrive;
import frc.robot.commands.teleop_Shooter;
import frc.robot.commands.teleop_Climber;
import frc.robot.commands.teleop_Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.music.Orchestra;

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
  public static teleop_ArcadeDrive arcadeDrive = new teleop_ArcadeDrive();
  public static DriveTrain driveTrain = new DriveTrain();
  public static Limelight limelight = new Limelight();
  public static OI m_oi = new OI();
  public static Orchestra music;
  private Command m_autonomousCommand;
  public static AHRS ahrs = new AHRS(SPI.Port.kMXP);
  public static teleop_Intake tIntake = new teleop_Intake();
  public static Intake intake = new Intake();
  public static Climber climber = new Climber();
  public static Shooter shooter = new Shooter();
  public static teleop_Shooter tShooter = new teleop_Shooter();
  public static teleop_Climber tClimber = new teleop_Climber();

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
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
    driveTrain.changeMode();
    m_autonomousCommand = m_oi.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    Robot.intake.openCompressor();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    driveTrain.changeMode();
    Robot.intake.openCompressor();
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
    music = new Orchestra();
    music.addInstrument(driveTrain.frontRight);
    music.addInstrument(driveTrain.frontLeft);
    music.addInstrument(driveTrain.backRight);
    music.addInstrument(driveTrain.backLeft);
    music.addInstrument(shooter.backMotor);
    music.addInstrument(shooter.frontMotor);
    music.loadMusic("gummy.chrp");
    music.play();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  public static void updateShuffleboard() {
    // Adds NavX values to Shuffle Board
    SmartDashboard.putNumber("Yaw", ahrs.getYaw());
    SmartDashboard.putNumber("Acceleration X", (double) (Math.round(ahrs.getWorldLinearAccelX() * 1000)) / 1000);
    SmartDashboard.putNumber("Acceleration Y", (double) (Math.round(ahrs.getWorldLinearAccelY() * 1000)) / 1000);
    SmartDashboard.putNumber("Velocity X", (double) (Math.round(ahrs.getVelocityX() * 1000)) / 1000);
    SmartDashboard.putNumber("Velocity Y", (double) (Math.round(ahrs.getVelocityY() * 1000)) / 1000);

    SmartDashboard.putString("Encoder Degrees", Robot.driveTrain.allEncoder());

    // Adds controller values to Shuffle Board
    SmartDashboard.putNumber("Right Trigger", (double) (Math.round(m_oi.getDriverRightTrigger() * 1000)) / 1000);
    SmartDashboard.putNumber("Left Trigger", (double) (Math.round(m_oi.getDriverLeftTrigger() * 1000)) / 1000);
    SmartDashboard.putNumber("Left Stick", (double) (Math.round(m_oi.getDriverRawJoystick() * 1000)) / 1000);
    // Adds Pneumatics to Shuffle Board
    // SmartDashboard.putNumber("Compresser Pressure", pneu.compressorReading());
  }
}
