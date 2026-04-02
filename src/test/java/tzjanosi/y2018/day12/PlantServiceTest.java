package tzjanosi.y2018.day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PlantServiceTest {
    @Test
    void potCounterTest() {
        ReadData readData = new ReadData("testInput.txt");
        PlantService plantService = new PlantService(readData.getOutput());
        assertEquals(325, plantService.potCounter(20));
    }

    @Test
    void potCounterProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        PlantService plantService = new PlantService(readData.getOutput());
        assertEquals(3798, plantService.potCounter(20));
    }

    @ParameterizedTest
    @ValueSource(ints = {20000, 30000, 40000, 50000, 60000, 700000})
    void findIncrementTest(int input) {
        ReadData readData = new ReadData("input.txt");
        PlantService plantService = new PlantService(readData.getOutput());
        assertEquals(plantService.potCounter(input), plantService.quickPotCounter(input));
    }

    @Test
    void quickPotCounterProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        PlantService plantService = new PlantService(readData.getOutput());
        assertEquals(3900000002212L, plantService.quickPotCounter(50000000000L));
    }
}