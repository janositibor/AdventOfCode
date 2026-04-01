package tzjanosi.y2018.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantServiceTest {
    @Test
    void potCounterTest() {
        ReadData readData = new ReadData("testInput.txt");
        PlantService plantService = new PlantService(readData.getOutput());
//        System.out.println(plantService.getRules());
        assertEquals(325, plantService.potCounter(20));
//        System.out.println(plantService.applyRules());
    }

    @Test
    void potCounterProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        PlantService plantService = new PlantService(readData.getOutput());
//        System.out.println(plantService.getRules());
        assertEquals(3798, plantService.potCounter(20));
//        System.out.println(plantService.applyRules());

    }


}