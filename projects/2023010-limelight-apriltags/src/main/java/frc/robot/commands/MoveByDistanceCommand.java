package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Command to move by distance.
 */
public class MoveByDistanceCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final double distanceToTravel;
    private final double motorOutput;
    private double initialEncoderDistance = 0;

    public MoveByDistanceCommand(DriveSubsystem driveSubsystem, double distance, double percentOutput) {
        this.driveSubsystem = driveSubsystem;
        distanceToTravel = distance;
        motorOutput = percentOutput;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.resetDistanceTraveled();
        initialEncoderDistance = driveSubsystem.getDistanceTraveled();
        driveSubsystem.move(motorOutput, motorOutput);
    }

    @Override
    public boolean isFinished() {
        SmartDashboard.putNumber("Encoder Dist", driveSubsystem.getDistanceTraveled());
        return Math.abs(driveSubsystem.getDistanceTraveled() - initialEncoderDistance) >= distanceToTravel;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.move(0, 0);
    }
}
