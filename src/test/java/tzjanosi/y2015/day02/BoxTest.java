package tzjanosi.y2015.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {
    @Test
    void calculateSurfaceForWrapper() {
        Box box1 = new Box(2, 3, 4);
        assertEquals(58, box1.calculateSurfaceForWrapper());
        Box box2 = new Box(1, 1, 10);
        assertEquals(43, box2.calculateSurfaceForWrapper());
    }

    @Test
    void incorrectArgumentOrder() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> new Box(2, 3, 2));
        assertEquals(String.format("The third argument should be the longest: %d, %d, %d", 2, 3, 2), iae.getMessage());
    }

    @Test
    void calculateRibbonLengthForWrapper() {
        Box box1 = new Box(2, 3, 4);
        assertEquals(34, box1.calculateRibbonLengthForWrapper());
        Box box2 = new Box(1, 1, 10);
        assertEquals(14, box2.calculateRibbonLengthForWrapper());
    }

}