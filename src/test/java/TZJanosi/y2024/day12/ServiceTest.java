package TZJanosi.y2024.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void GardenServiceTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(140,service.calculateCost());
    }
    @Test
    void GardenService2Test(){
        ReadData readData=new ReadData("testInput2.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(772,service.calculateCost());
    }
    @Test
    void GardenService3Test(){
        ReadData readData=new ReadData("testInput3.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(1930,service.calculateCost());
    }
    @Test
    void GardenServiceWithProblemDataTest(){
        ReadData readData=new ReadData("input.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(1473408,service.calculateCost());
    }


}