package TZJanosi.y2015.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    @Test
    void init() {
        ReadData readData = new ReadData("input.txt");
        Driver driver = new Driver(readData.getOutput().get(0));
        assertEquals(8192, driver.getInput().length());
    }

    @Test
    void countVisitedHouses() {
        ReadData readData = new ReadData("input.txt");
        Driver driver = new Driver(readData.getOutput().get(0));
        assertEquals(2081, driver.countVisitedHouses());
    }

}