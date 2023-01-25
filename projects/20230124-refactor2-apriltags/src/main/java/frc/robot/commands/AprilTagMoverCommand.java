package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.camera.CameraData;

public class AprilTagMoverCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final CameraSubsystem cameraSubsystem;

    public AprilTagMoverCommand(DriveSubsystem driveSubsystem, CameraSubsystem cameraSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.cameraSubsystem = cameraSubsystem;
        addRequirements(driveSubsystem);
        addRequirements(cameraSubsystem);
    }

    @Override
    public void execute() {
        CameraData camResult = cameraSubsystem.getTarget();
        double translation = 0;
        if (camResult.hasTarget) {
            if (camResult.targetDistance > 1.1) translation = 0.3;
            else if (camResult.targetDistance < 0.9) translation = -0.3;
        }
        driveSubsystem.move(translation, translation);
    }
}
