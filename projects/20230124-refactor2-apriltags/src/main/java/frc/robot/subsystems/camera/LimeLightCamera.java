package frc.robot.subsystems.camera;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightCamera implements CameraInterface {

    private static String cameraName = "limelight";

    @Override
    public void close() {
        // Nothing to do.
    }

    @Override
    public CameraData getTarget() {
        CameraData retVal = new CameraData();
        NetworkTable cam = NetworkTableInstance.getDefault().getTable(cameraName);
        if(cam.containsKey("getpipe")) retVal.isConnected = true;
        if (retVal.isConnected) {
            if (cam.getEntry("tv").getDouble(0) == 1) retVal.hasTarget = true;
            if (retVal.hasTarget) {
                retVal.latencyMillis = cam.getEntry("tl").getDouble(0);
                retVal.id = (int)cam.getEntry("tid").getDouble(-1);
                double[] camPose = cam.getEntry("campose").getDoubleArray(new double[0]);
                retVal.targetDistance = Math.abs(camPose[2]);
                retVal.targetAngle = cam.getEntry("tx").getDouble(-1);
            }
        }

        toDashboard(retVal);
        return retVal;
    }
}
