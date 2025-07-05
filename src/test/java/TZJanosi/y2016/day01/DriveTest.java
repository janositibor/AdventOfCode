package TZJanosi.y2016.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriveTest {
    @Test
    void testData1() {
        Drive drive = new Drive("R2, L3");
        int distance = drive.process();
        assertEquals(new Coordinate(2, 3), drive.getLocation());
        assertEquals(5, distance);
    }

    @Test
    void testData2() {
        Drive drive = new Drive("R2, R2, R2");
        int distance = drive.process();
        assertEquals(new Coordinate(0, -2), drive.getLocation());
        assertEquals(2, distance);
    }

    @Test
    void testData3() {
        Drive drive = new Drive("R5, L5, R5, R3");
        int distance = drive.process();
        assertEquals(12, distance);
    }

    @Test
    void problemData() {
        ReadData readData = new ReadData("input.txt");
        Drive drive = new Drive(readData.getOutput().get(0));
        int distance = drive.process();
        assertEquals(246, distance);
    }

}