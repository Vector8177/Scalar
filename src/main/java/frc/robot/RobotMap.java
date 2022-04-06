package frc.robot;

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
        public static final double DRIVE_SPEED_MODIFIER = 1;
        public static final double LEFT_JOYSTICK_SPEED_MODIFIER = 1;
        public static final double TURN_SPEED_MODIFIER = .4;
        public static final double AUTONOMOUS_SPEED = .65;

        // Distance
        public static final double FT_PER_ENCODER_DEGREE = 13703;
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

        // Ziegler Nichols Method; See wiki PID article
        // T = .28; kP oscillation = .28;
        public static final Gains frontGains = new Gains(.14, 0, 0, .0495, 300, 1);
        // T = .28; kP oscillation = .24;
        public static final Gains backGains = new Gains(.12, 0, 0, .0495, 300, 1);
    }

    public static final class Climber {
        // Maximum Encoder Degree Values For Climbers
        public static final double UNEXTENDED_LEFT_CLIMBER_MAX = -225825;
        public static final double UNEXTENDED_RIGHT_CLIMBER_MAX = 220135;
        public static final double EXTENDED_LEFT_CLIMBER_MAX = -315118;
        public static final double EXTENDED_RIGHT_CLIMBER_MAX = 342550;

        // Speed
        public static final double CLIMBER_TELEOP_SPEED = 1;
        public static final double CLIMBER_DPAD_SPEED = .5;
    }
}
