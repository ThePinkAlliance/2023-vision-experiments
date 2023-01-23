package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoMoveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private double leftDrive = 0;
    private double rightDrive = 0;

    
    public AutoMoveCommand(DriveSubsystem driveSubsystem, double leftDrive, double rightDrive) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
    }

    @Override
    public void execute() {
        driveSubsystem.move(leftDrive, rightDrive);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.move(0, 0);
    }
    
    
}
