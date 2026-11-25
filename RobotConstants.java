package org.firstinspires.ftc.teamcode;

/**
 * Single place for every tuned value used by TeleOp and Autonomous.
 * Every number here must come from a test you can show a judge.
 */
public class RobotConstants {

    // ---------- Drive (TeleOp) ----------
    public static final double DRIVE_SPEED = 0.5;       // normal speed cap
    public static final double SLOW_SPEED = 0.25;       // hold right bumper for precision
    public static final double STRAFE_GAIN = 1.1;       // mecanum strafes weaker than it drives
    // Back-left needs 1.5x the power of the others (was a hard-coded 0.75 vs 0.5).
    public static final double BACK_LEFT_TRIM = 1.5;

    // ---------- Shooter ----------
    // Shooter base power at the nominal battery voltage
    public static final double SHOOTER_BASE_POWER = 0.7;

    // ENCODER TeleOp target shooter speed in encoder ticks per second
    public static final double SHOOTER_TARGET_TPS = 1900;

    // ENCODER TeleOp only: shooter counts as "at speed" when within this fraction of the target speed
    public static final double SHOOTER_TOLERANCE = 0.05;

    // NO-ENCODER TeleOp only: battery voltage that SHOOTER_BASE_POWER was tuned at.
    public static final double NOMINAL_VOLTAGE = 12.0;

    // Feed wheel power
    public static final double GECKO_POWER = 0.5;

    // Right trigger must be pressed past this to count as "shooting"
    public static final double TRIGGER_THRESHOLD = 0.1;

    // ---------- Intake ----------
    // Power used when the left bumper reverses the intake (outtake)
    public static final double OUTTAKE_POWER = 0.5;

    // ---------- Autonomous (park) ----------
    public static final double PARK_POWER = 0.6;
    public static final long PARK_TIME_MS = 1000;
}