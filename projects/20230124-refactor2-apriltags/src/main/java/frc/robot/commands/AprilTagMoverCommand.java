package frc.robot.commands;

import edu.wpi.first.math.Pair;
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
        double rightDriveSpeed = 0;
        double leftDriveSpeed = 0;
        CameraData camResult = cameraSubsystem.getTarget();
        if (camResult.pipelineType == PipelineType.APRIL_TAG) {
            if (camResult.hasTargets()) {
                double distance = camResult.getTargets().get(0).targetDistance;
                double angle = camResult.getTargets().get(0).targetXAngle;
                Pair<Double, Double> speeds = getDiffDriveValues(distance, angle);
                rightDriveSpeed = speeds.getSecond();
                leftDriveSpeed = speeds.getFirst();

            }
            driveSubsystem.move(leftDriveSpeed, rightDriveSpeed);
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

    /**
     * Calculate the left and right drive power based on distance and angle.
     * @param distance distance
     * @param angle angle
     * @return the power for the left and right drive
     */
    protected static Pair<Double, Double> getDiffDriveValues(double distance, double angle) {
        final double maxSpeed = 0.7;
        double speed = maxSpeed;
        if (distance > 4) speed = maxSpeed * 0.7;
        else if (distance > 2) speed = maxSpeed;
        else if (distance > 1.3) speed = maxSpeed * 0.8;
        else if (distance > 1) speed = maxSpeed * 0.5;
        else if (distance < 0.7) speed = -maxSpeed * 0.5;
        else speed = 0;

        double turnRatio = 0;
        if (Math.abs(angle) > 3) {
            turnRatio = 0.7;
        } else if (Math.abs(angle) > 7) {
            turnRatio = 0.9;
        }
        if (angle < 0) turnRatio *= -1;
        if (speed < 0) turnRatio *= -1;
        double rightDriveSpeed = speed + (speed * turnRatio);
        double leftDriveSpeed = speed - (speed * turnRatio);
        return new Pair<Double, Double>(leftDriveSpeed, rightDriveSpeed);
    }

}
