package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import frc.robot.Utility.Gains;

public class RobotMap {

    public static double convertRPMToMotorVel(double rpm) {
        return rpm * 2048 / 600;
    }

    public static double convertMotorVelToRPM(double motorVelocity) {
        return motorVelocity * 600 / 2048;
    }

    public static final class DriveTrain {
        // Motor ID
        public static final int FRONT_RIGHT_MOTOR_ID = 1;
        public static final int BACK_RIGHT_MOTOR_ID = 2;
        public static final int FRONT_LEFT_MOTOR_ID = 3;
        public static final int BACK_LEFT_MOTOR_ID = 4;

        // Speed
        public static final double DRIVE_SPEED_MODIFIER = .85;
        public static final double LEFT_JOYSTICK_SPEED_MODIFIER = .6;
        public static final double TURN_SPEED_MODIFIER = .4;
        public static final double AUTONOMOUS_SPEED = .65;

        // Distance
        public static final double FT_PER_ENCODER_DEGREE = 13703;

        // PID
        public static final Gains autoPID = new Gains(0.075, 0.0, 0.012, 0, 0, 0);
        public static final Gains tarmacPID = new Gains(0.075, 0.0, 0.012, 0, 0, 0);
    }

    public static final class Controller {
        // Controller ID
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int INTAKE_CONTROLLER_PORT = 1;

        // Button ID
        public static final int BUTTON_A = 1;
        public static final int BUTTON_B = 2;
        public static final int BUTTON_X = 3;
        public static final int BUTTON_Y = 4;
        public static final int LEFT_BUMPER = 5;
        public static final int RIGHT_BUMPER = 6;
    }

    public static final class Intake {
        public static final int PHEUMATICS_ID = 0;
        public static final double INTAKE_MOTOR_MODIFIER = .4;
    }

    public static final class Shooter {
        // Shooter Motor ID
        public static final int SHOOTER_FRONT_MOTOR_ID = 5;
        public static final int SHOOTER_BACK_MOTOR_ID = 6;

        // Default SmartDashboard Values
        public static final double BIG_WHEEL_SPEED = 2600;
        public static final double SMALL_WHEEL_SPEED = 1600;

        public static final double SHOOT_SPEED_MODIFIER = 0.98;

        // Ziegler Nichols Method; See wiki PID article
        // T = .28; kP oscillation = .28;
        public static final Gains frontGains = new Gains(.14, 0, 0, .0495, 300, 1);
        // T = .28; kP oscillation = .24;
        public static final Gains backGains = new Gains(.12, 0, 0, .0495, 300, 1);
    }

    public static final class Climber {
        // Maximum Encoder Degree Values For Climbers
        public static final double UNEXTENDED_LEFT_CLIMBER_MAX = -207112;
        public static final double UNEXTENDED_RIGHT_CLIMBER_MAX = 201781;
        public static final double EXTENDED_LEFT_CLIMBER_MAX = -290893;
        public static final double EXTENDED_RIGHT_CLIMBER_MAX = 296143;

        // Speed
        public static final double CLIMBER_TELEOP_SPEED = .85;
        public static final double CLIMBER_DPAD_SPEED = .5;
    }

    public static final class PathPlanner {
        public static final double ksVolts = 0.14792;
        public static final double kvVoltSecondsPerMeter = .011551;
        public static final double kaVoltSecondsSquaredPerMeter = 0.0017413;

        // Example value only - as above, this must be tuned for your drive!
        public static final double kPDriveVel = 0.015177;

        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;

        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;

        public static final double kTrackwidthMeters = 0.5207;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
                kTrackwidthMeters);
    }
}
