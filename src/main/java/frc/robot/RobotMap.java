package frc.robot;

public class RobotMap {
    // Motors
    public static final int FRONT_RIGHT_MOTOR_ID = 1;
    public static final int BACK_RIGHT_MOTOR_ID = 2;
    public static final int FRONT_LEFT_MOTOR_ID = 3;
    public static final int BACK_LEFT_MOTOR_ID = 4;
    public static final int UNITS_PER_REVOLUTION = 12185;

    // PID control
    public static final double kP = 0.03;
    public static final double kI = 0.00;
    public static final double kD = 0.00;
    public static final double kF = 0.00;

    // Controller
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;

    // Speed
    public static final double LEFT_MOTOR_SPEED_MODIFIER = .5;
    public static final double RIGHT_MOTOR_SPEED_MODIFIER = .5;
    public static final double LEFT_JOYSTICK_SPEED_MODIFIER = .8;
    public static final double AUTONOMOUS_SPEED = .4;
    public static final double CORRECTION_SPEED = .1;

    // Distance
    public static final double DISTANCE_PER_REVOLUTION_FT = 1.5708;

    // Degrees
    public static final double DEGREES_PER_REVOLUTION = 79.925;
    public static final double EXTRA_DEGREES_PER_DEGREE = 1.75;
}
