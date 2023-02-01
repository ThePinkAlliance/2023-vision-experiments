package frc.robot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.wpi.first.math.Pair;

public class AprilTagMoverCOmmandTests {
    @Test
    public void diffDriveValuesTest() {
        Pair<Double, Double> values;

        // Stopped
        values = AprilTagMoverCommand.getDiffDriveValues(0.9, 0);
        assertEquals(0, values.getFirst().doubleValue());
        assertEquals(0, values.getSecond().doubleValue());

        // Forwards
        values = AprilTagMoverCommand.getDiffDriveValues(2, 0);
        assertTrue(values.getFirst().doubleValue() > 0);
        assertTrue(values.getSecond().doubleValue() > 0);

        // Backwards
        values = AprilTagMoverCommand.getDiffDriveValues(0.2, 0);
        assertTrue(values.getFirst().doubleValue() < 0);
        assertTrue(values.getSecond().doubleValue() < 0);

        // Turn left to line up
        values = AprilTagMoverCommand.getDiffDriveValues(1.2, 10);
        assertTrue(values.getFirst().doubleValue() > 0);
        assertTrue(values.getSecond().doubleValue() > 0);
        assertTrue(values.getFirst().doubleValue() < values.getSecond().doubleValue());

        // Turn right to line up
        values = AprilTagMoverCommand.getDiffDriveValues(1.2, -10);
        assertTrue(values.getFirst().doubleValue() > 0);
        assertTrue(values.getSecond().doubleValue() > 0);
        assertTrue(values.getFirst().doubleValue() > values.getSecond().doubleValue());
    }
}
