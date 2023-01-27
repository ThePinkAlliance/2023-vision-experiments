package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.camera.CameraData;
import frc.robot.subsystems.camera.CameraInterface.PipelineType;

public class AprilTagMoverCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final CameraSubsystem cameraSubsystem;
    private boolean findReflectiveTarget = false;

    public AprilTagMoverCommand(DriveSubsystem driveSubsystem, CameraSubsystem cameraSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.cameraSubsystem = cameraSubsystem;
        addRequirements(driveSubsystem);
        addRequirements(cameraSubsystem);
        cameraSubsystem.setPipeline(PipelineType.APRIL_TAG);
    }

    @Override
    public void execute() {
        if (!findReflectiveTarget) {
            driveCloserToTarget();
        } else {
            findReflectiveTarget();
        }
    }

    private void driveCloserToTarget() {
        CameraData camResult = cameraSubsystem.getTarget();
        if (camResult.pipelineType == PipelineType.APRIL_TAG) {
            double translation = 0;
            if (camResult.hasTargets()) {
                if (camResult.getTargets().get(0).targetDistance > 1.1) translation = 0.3;
                else if (camResult.getTargets().get(0).targetDistance < 0.9) translation = -0.3;
                else {
                    cameraSubsystem.setPipeline(PipelineType.REFLECTIVE);
                    findReflectiveTarget = true;
                }
            }
            driveSubsystem.move(translation, translation);
        } else System.out.println("Waiting for pipeline to switch to APRIL TAG mode");
    }

    private void findReflectiveTarget() {
        CameraData camResult = cameraSubsystem.getTarget();
        if (camResult.pipelineType == PipelineType.REFLECTIVE) {
            if (camResult.hasTargets()) {
                if (camResult.getTargets().get(0).targetXAngle < -5) driveSubsystem.move(-0.3, 0.3);
                else if (camResult.getTargets().get(0).targetXAngle > 5) driveSubsystem.move(0.3, -0.3);
                else { 
                    driveSubsystem.move(0, 0); 
                    cameraSubsystem.setPipeline(PipelineType.APRIL_TAG);
                    findReflectiveTarget = false;
                }
            } else driveSubsystem.move(0, 0);
        } else System.out.println("Waiting for pipeline to switch to REFLECTIVE mode");
    }

}
