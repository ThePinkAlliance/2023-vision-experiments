package frc.robot.commands;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.PhotonVisionCamera;

public class AprilTagMoverCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    PhotonVisionCamera camera;

    public AprilTagMoverCommand(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        camera = new PhotonVisionCamera();
        super.initialize();
    }

    @Override
    public void execute() {
        SmartDashboard.putBoolean("Cam connected", camera.isConnected());
        PhotonPipelineResult camResult = camera.getLatest();
        SmartDashboard.putBoolean("Cam Tag detected", camResult.hasTargets());
        SmartDashboard.putNumber("Cam Latency", camResult.getLatencyMillis());
        if (camResult.hasTargets()) {
            PhotonTrackedTarget target = camResult.getBestTarget();
            SmartDashboard.putNumber("Cam X", target.getBestCameraToTarget().getX());
            SmartDashboard.putNumber("Cam Y", target.getBestCameraToTarget().getY());
            SmartDashboard.putNumber("Cam Z", target.getBestCameraToTarget().getZ());

            if (target.getBestCameraToTarget().getX() > 1.1) driveSubsystem.move(0.3, 0.3);
            else if (target.getBestCameraToTarget().getX() < 0.9) driveSubsystem.move(-0.3, -0.3);
            else driveSubsystem.move(0, 0);
        } else driveSubsystem.move(0, 0);

    }

    @Override
    public void end(boolean interrupted) {
        camera.close();
        camera = null;
    }
}
