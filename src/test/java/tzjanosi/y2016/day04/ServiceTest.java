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

    @Test
    void decrypterTest() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        service.getRooms().get(0).decryptName();
        assertEquals("very encrypted name", service.getRooms().get(0).getDecryptedName());
    }

    @Test
    void problemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(267, service.sectorIDForNorthPoleObjects());
    }

}