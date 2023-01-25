package frc.robot.subsystems.camera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Generic camera interface.
 */
public interface CameraInterface {
    /**
     * Get closest target data.
     * @return closest target data
     */
    public CameraData getTarget();

    /**
     * Close the connection to the camera.
     */
    public void close();

    /**
     * Write the camera data to the dashboard.
     * @param data camera data
     */
    default void toDashboard(CameraData data) {
        SmartDashboard.putBoolean("Cam connected", data.isConnected);
        SmartDashboard.putBoolean("Cam Target detected", data.hasTarget);
        SmartDashboard.putNumber("Cam Latency", data.latencyMillis);
        SmartDashboard.putNumber("Cam Target Distance", data.targetDistance);
        SmartDashboard.putNumber("Cam Target Angle", data.targetAngle);
    }
}
