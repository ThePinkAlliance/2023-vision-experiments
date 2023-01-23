package frc.robot.utils;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class PhotonVisionCamera {
    private PhotonCamera camera;
    private static String cameraName = "main";

    public PhotonVisionCamera() {
        camera = new PhotonCamera(cameraName);
    }

    public boolean isConnected() {
        return camera.isConnected();
    }

    public PhotonPipelineResult getLatest() {
        if (isConnected())
            return camera.getLatestResult();
        else 
            return null;
    }

    public PhotonTrackedTarget getBestTarget() {
        if (isConnected())
            return camera.getLatestResult().getBestTarget();
        else 
            return null;
    }

    public void close() {
        if (isConnected()) 
            camera.close();
    }
}
