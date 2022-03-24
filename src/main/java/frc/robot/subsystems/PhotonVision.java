// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonVision extends SubsystemBase {
    PhotonPipelineResult result;
    PhotonCamera limelight = new PhotonCamera("vectorcam");

    /** Creates a new DriveTrian. */
    public PhotonVision() {
    }

    @Override
    public void periodic() {
        result = limelight.getLatestResult();
    }

    public PhotonTrackedTarget bestTarget() {
        if (result.hasTargets()) {
            return result.getBestTarget();
        } else {
            return null;
        }
    }

    public double getYaw() {
        if (result.hasTargets()) {
            return result.getBestTarget().getYaw();
        }
        return 0;
    }

    public double getDistance() {
        if (result.hasTargets()) {
            return Units.metersToFeet(
                    PhotonUtils.calculateDistanceToTargetMeters(Units.inchesToMeters(31), Units.inchesToMeters(105.75),
                            Units.degreesToRadians(50),
                            Units.degreesToRadians(bestTarget().getPitch())));
        }
        return 1;
    }
}