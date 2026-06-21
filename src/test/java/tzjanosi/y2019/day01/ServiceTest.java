package tzjanosi.y2019.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void calculateFuelRequirementsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(3152375, service.calculateFuelRequirements());
    }

    @Test
    void advancedFuelTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(2, service.advancedFuel(14));
        assertEquals(966, service.advancedFuel(1969));
        assertEquals(50346, service.advancedFuel(100756));
    }

    @Test
    void calculateAdvancedFuelRequirementsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(4725720, service.calculateAdvancedFuelRequirements());
    }


}