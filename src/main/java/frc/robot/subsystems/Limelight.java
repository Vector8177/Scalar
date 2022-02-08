// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.*;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class Limelight extends SubsystemBase {
    PhotonCamera camera;
    PhotonPipelineResult result;

    /** Creates a new DriveTrian. */
    public Limelight() {
        camera = new PhotonCamera("gloworm");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public PhotonPipelineResult getLatestResult() {
        return camera.getLatestResult();
    }

    public void setDriverMode(boolean driver) {
        camera.setDriverMode(driver);
    }

    public PhotonTrackedTarget retriveBestTarget() {
        result = getLatestResult();
        System.out.println(result.hasTargets());
        if (result.hasTargets()) {
            return result.getBestTarget();
        }
        return null;
    }

}