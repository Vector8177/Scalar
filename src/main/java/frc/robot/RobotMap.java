package frc.robot;

public class RobotMap {
    // Motors
    public static final int FRONT_RIGHT_MOTOR_ID = 1;
    public static final int BACK_RIGHT_MOTOR_ID = 2;
    public static final int FRONT_LEFT_MOTOR_ID = 3;
    public static final int BACK_LEFT_MOTOR_ID = 4;
    public static final int UNITS_PER_REVOLUTION = 21934;

    // PID control
    public static final double kP = .02;
    public static final double kI = 0.0008;
    public static final double kD = 0.0022;
    public static final double kF = 0.00;

    // PID for straight forward
    public static final double aP = 0.000025;
    public static final double aI = 0;
    public static final double aD = 0.000000006;
    public static final double aF = 0.00;

    // Controller
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int INTAKE_CONTROLLER_PORT = 1;
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;

    // Pneumatics
    public static final int PHEUMATICS_ID = 0;
    public static final double INTAKE_MOTOR_MODIFIER = .4;

    // Shooter
    public static final int SHOOTER_FRONT_MOTOR_ID = 5;
    public static final int SHOOTER_BACK_MOTOR_ID = 6;
    // public static final double DISTANCE_CALCULATOR = equation based on distance

    // Speed
    public static final double DRIVE_SPEED_MODIFIER = .75;
    public static final double LEFT_JOYSTICK_SPEED_MODIFIER = 1;
    public static final double TURN_SPEED_MODIFIER = .8;
    public static final double AUTONOMOUS_SPEED = .40;
    public static final double BIG_WHEEL_SPEED = .375;
    public static final double SMALL_WHEEL_SPEED = -.5;

    // Distance
    public static final double FT_PER_ENCODER_DEGREE = 13703;
    public static final double DISTANCE_PER_REVOLUTION_FT = 5 * Math.PI / 12;
    // 1.5708

    // Degrees
    public static final double DEGREES_PER_REVOLUTION = 79.925;
    public static final double EXTRA_DEGREES_PER_DEGREE = 1.75;
}
