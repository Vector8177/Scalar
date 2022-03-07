// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import com.ctre.phoenix.music.Orchestra;

public class PlayMusic extends CommandBase {
    Orchestra music = new Orchestra();
    String songName;

    /** Creates a new ArcadeDrive. */
    public PlayMusic(String songName) {
        this.songName = songName;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        music.addInstrument(Robot.driveTrain.frontRight);
        music.addInstrument(Robot.driveTrain.frontLeft);
        music.addInstrument(Robot.driveTrain.backRight);
        music.addInstrument(Robot.driveTrain.backLeft);
        music.addInstrument(Robot.shooter.backMotor);
        music.addInstrument(Robot.shooter.frontMotor);
        music.loadMusic(songName);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        music.play();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        music.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !music.isPlaying();
    }
}
