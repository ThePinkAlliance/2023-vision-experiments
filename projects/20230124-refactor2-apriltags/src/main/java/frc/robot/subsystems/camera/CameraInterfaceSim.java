package frc.robot.subsystems.camera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Allows for camera simulation via Network Tables (SmartDashboard) keys.
 * NOTE: I was not able to make a real simulated device. I could not find enough information
 * online on how to do that.
 */
public class CameraInterfaceSim implements CameraInterface {
    // Dashboard key definitions.
    private static final String CAM_CONNECTED_KEY = "SimCam connected";
    private static final String CAM_HAS_TARGET_KEY = "SimCam has target";
    private static final String CAM_ID_KEY = "SimCam target id";
    private static final String CAM_LATENCY_KEY = "SimCam latency millis";
    private static final String CAM_ANGLE_KEY = "SimCam target angle";
    private static final String CAM_DISTANCE_KEY = "SimCam target distance (m)";

    public CameraInterfaceSim() {
        // Initialize the dashboard, so we can set the values.
        SmartDashboard.putBoolean(CAM_CONNECTED_KEY, false);
        SmartDashboard.putBoolean(CAM_HAS_TARGET_KEY, false);
        SmartDashboard.putNumber(CAM_ID_KEY, 5);
        SmartDashboard.putNumber(CAM_LATENCY_KEY, 40000);
        SmartDashboard.putNumber(CAM_ANGLE_KEY, 0);
        SmartDashboard.putNumber(CAM_DISTANCE_KEY, 1.0);
    }

    @Override
    public CameraData getTarget() {
        CameraData retVal = new CameraData();
        retVal.isConnected = SmartDashboard.getBoolean(CAM_CONNECTED_KEY, false);
        retVal.hasTarget = SmartDashboard.getBoolean(CAM_HAS_TARGET_KEY, false);
        retVal.id = (int)SmartDashboard.getNumber(CAM_ID_KEY, 5);
        retVal.latencyMillis = SmartDashboard.getNumber(CAM_LATENCY_KEY, 40000);
        retVal.targetAngle = SmartDashboard.getNumber(CAM_ANGLE_KEY, 0);
        retVal.targetDistance = SmartDashboard.getNumber(CAM_DISTANCE_KEY, 1.0);
        return retVal;
    }

    @Override
    public void close() {
       // Nothing to do.
    }

    
}
