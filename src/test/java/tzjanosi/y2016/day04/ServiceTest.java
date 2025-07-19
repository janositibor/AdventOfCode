package tzjanosi.y2016.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void testData() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(1514, service.calculateSumOfSectorIDsForRealRooms());
    }

    @Test
    void problemData() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(278221, service.calculateSumOfSectorIDsForRealRooms());
    }

}