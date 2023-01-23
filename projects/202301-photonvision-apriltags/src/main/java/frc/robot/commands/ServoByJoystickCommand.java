package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ServoSubsystem;

public class ServoByJoystickCommand extends CommandBase {
    private final ServoSubsystem servoSubsystem;
    private final DoubleSupplier servoValue;

    public ServoByJoystickCommand(ServoSubsystem servoSubsystem, DoubleSupplier value) {
        this.servoSubsystem = servoSubsystem;
        this.servoValue = value;
    }

    @Override
    public void execute() {
        servoSubsystem.move(servoValue.getAsDouble());
    }
}
