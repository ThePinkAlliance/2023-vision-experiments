package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.camera.CameraData;
import frc.robot.subsystems.camera.CameraInterface;
import frc.robot.subsystems.camera.CameraInterfaceSim;
import frc.robot.subsystems.camera.LimeLightCamera;
import frc.robot.subsystems.camera.PhotonVisionCamera;

public class CameraSubsystem extends SubsystemBase {
    private CameraInterface camera = new PhotonVisionCamera();
    //private CameraInterface camera = new LimeLightCamera();
    
    public CameraSubsystem() {
        if (!Robot.isReal()) camera = new CameraInterfaceSim();
        else camera = new PhotonVisionCamera();
    }

    public CameraData getTarget() {
        return camera.getTarget();
    }
}
