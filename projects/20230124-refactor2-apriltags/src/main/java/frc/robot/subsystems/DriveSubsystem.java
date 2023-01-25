// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class DriveSubsystem extends SubsystemBase {
  private final TalonSRX leftFrontDrive = new TalonSRX(2);
  private final TalonSRX rightFrontDrive = new TalonSRX(1);
  private final TalonSRX leftRearDrive = new TalonSRX(3);
  private final TalonSRX rightRearDrive = new TalonSRX(4);

  // Only encoder 4 is working on PizzaBot
  private final Encoder motorEncoder4 = new Encoder(6, 7);

  private final AnalogGyro gyro = new AnalogGyro(0);

  private static final double JOYSTICK_GAIN = 0.5;
  
  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
    // Initialize the motor properties
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = 40; // the peak current, in amps
    config.peakCurrentDuration = 1500; // the time at the peak current before the limit triggers, in ms
    config.continuousCurrentLimit = 30; // the current to maintain if the peak limit is triggered

    leftFrontDrive.configAllSettings(config);
    leftRearDrive.configAllSettings(config);

    rightFrontDrive.configAllSettings(config);
    rightRearDrive.configAllSettings(config);
    rightFrontDrive.setInverted(true);
    rightRearDrive.setInverted(true);

    motorEncoder4.setDistancePerPulse(4./256.);
    motorEncoder4.reset();

    // Simulate the inputs
    if (!Robot.isReal())
      new EncoderSim(motorEncoder4);
      new AnalogGyroSim(gyro);
  }

  public void move(double leftDrive, double rightDrive) {
      SmartDashboard.putNumber("Left Drive Input", leftDrive);
      moveMotor(leftFrontDrive, leftDrive);
      moveMotor(leftRearDrive, leftDrive);

      SmartDashboard.putNumber("Right Drive Input", rightDrive);
      moveMotor(rightFrontDrive, rightDrive);
      moveMotor(rightRearDrive, rightDrive);
      
      SmartDashboard.putNumber("Encoder Rate", motorEncoder4.getRate());
      SmartDashboard.putNumber("Encoder Raw", motorEncoder4.getRaw());
      SmartDashboard.putNumber("Encoder Dist4", motorEncoder4.getDistance());
      SmartDashboard.putNumber("Gyro", gyro.getRate());
  }

  public double getDistanceTraveled() {
    return motorEncoder4.getDistance();
  }

  public void resetDistanceTraveled() {
    motorEncoder4.reset();
  }

  private void moveMotor(TalonSRX motor, double value) {
    double modifiedValue = value * JOYSTICK_GAIN;
    if (value > 1) modifiedValue = 1;
    else if (value < -1) modifiedValue = -1;
    motor.set(TalonSRXControlMode.PercentOutput, modifiedValue);
  }
}
