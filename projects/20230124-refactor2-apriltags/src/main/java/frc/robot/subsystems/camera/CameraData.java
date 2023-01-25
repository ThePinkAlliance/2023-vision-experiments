package frc.robot.subsystems.camera;

/**
 * Best target data from the camera.
 */
public class CameraData {
    /**
     * Is the camera connected to the robot.
     */
    public boolean isConnected = false;

    /**
     * True if the camera has a target. Always check this before obtaining other target data.
     */
    public boolean hasTarget = false;
    
    /**
     * The latency reported by the camera on obtaining the target information.
     */
    public double latencyMillis = 0;

    /**
     * The target ID. For AprilTags this is the fudicial ID.
     */
    public int id = -1;

    /**
     * The horizontal (left or right) angle to the target, 0 being in front, positive
     * to the right and negative to the left.
     */
    public double targetAngle = -1;

    /**
     * Distance to the target. Cannot be negative.
     */
    public double targetDistance = 0;
}
