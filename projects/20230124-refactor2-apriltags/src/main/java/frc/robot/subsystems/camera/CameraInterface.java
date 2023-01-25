package frc.robot.subsystems.camera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public interface CameraInterface {
    public CameraData getTarget();
    public void close();
    default void toDashboard(CameraData data) {
        SmartDashboard.putBoolean("Cam connected", data.isConnected);
        SmartDashboard.putBoolean("Cam Target detected", data.hasTarget);
        SmartDashboard.putNumber("Cam Latency", data.latencyMillis);
        SmartDashboard.putNumber("Cam Target Distance", data.targetDistance);
        SmartDashboard.putNumber("Cam Target Angle", data.targetAngle);
    }
}
