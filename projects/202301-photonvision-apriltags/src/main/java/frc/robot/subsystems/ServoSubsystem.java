package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSubsystem extends SubsystemBase {
    private Servo servo = new Servo(0);

    public void move(double value) {
        
        double modifiedValue = value;
        if (modifiedValue < 0) modifiedValue = 0;
        else if (modifiedValue > 1) modifiedValue = 1;
        servo.set(modifiedValue);
        SmartDashboard.putNumber("Servo value", value);
    }
    
}
