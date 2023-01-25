// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoCommandGroup;
import frc.robot.commands.MoveCommand;
import frc.robot.commands.ServoByJoystickCommand;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static final int LEFT_JOY_AXIS = 1;
  private static final int RIGHT_JOY_AXIS = 5;
  private static final int SERVO_JOY_AXIS = 2;
  private static final Joystick joystick = new Joystick(0);
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ServoSubsystem servoSubsystem = new ServoSubsystem();
  private final CameraSubsystem cameraSubsystem = new CameraSubsystem();

  private final Command moveCommand = new MoveCommand(driveSubsystem, 
                                                          () -> joystick.getRawAxis(LEFT_JOY_AXIS), 
                                                          () -> joystick.getRawAxis(RIGHT_JOY_AXIS));
  private final Command autoMoveCommand = new AutoCommandGroup(driveSubsystem, cameraSubsystem);                                                          
  private final Command servoByJoystickCommand = new ServoByJoystickCommand(servoSubsystem, 
  () -> joystick.getRawAxis(SERVO_JOY_AXIS));      

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoMoveCommand;
  }

  public Command getMoveCommand() {
    return moveCommand;
  }

  public Command getServoByJoystickCommand() {
    return servoByJoystickCommand;
  }
}
