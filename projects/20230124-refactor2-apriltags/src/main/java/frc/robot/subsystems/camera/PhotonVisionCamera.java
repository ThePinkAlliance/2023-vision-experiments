package frc.robot.subsystems.camera;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

/**
 * Fetch the best target from a PhotonVision camera.
 */
public class PhotonVisionCamera implements CameraInterface {
    private PhotonCamera camera;
    private static String cameraName = "main";

    public PhotonVisionCamera() {
        camera = new PhotonCamera(cameraName);
    }

    @Override
    public void close() {
        if (camera.isConnected()) 
            camera.close();
    }

    @Override
    public CameraData getTarget() {
        CameraData retVal = new CameraData();
        retVal.isConnected = camera.isConnected();
        if (retVal.isConnected) {
            PhotonPipelineResult results = camera.getLatestResult();
            retVal.hasTarget = results.hasTargets();
            if (retVal.hasTarget) {
                retVal.latencyMillis = results.getLatencyMillis();
                PhotonTrackedTarget target = results.getBestTarget();
                retVal.id = target.getFiducialId();
                retVal.targetDistance = target.getBestCameraToTarget().getX();
                retVal.targetAngle = target.getYaw();
            }
        }

        toDashboard(retVal);
        return retVal;
    }

    @Override
    protected void finalize() throws Throwable {
        if (camera.isConnected()) camera.close();
    }
}
