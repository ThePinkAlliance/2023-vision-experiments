package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class MoveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier leftDriveValue;
    private final DoubleSupplier rightDriveValue;

    public MoveCommand(DriveSubsystem driveSubsystem, DoubleSupplier leftDrive, DoubleSupplier rightDrive) {
        this.driveSubsystem = driveSubsystem;
        this.leftDriveValue = leftDrive;
        this.rightDriveValue = rightDrive;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.move(leftDriveValue.getAsDouble(), rightDriveValue.getAsDouble());
    }
}
