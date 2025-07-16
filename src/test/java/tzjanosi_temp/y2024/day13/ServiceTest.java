package tzjanosi_temp.y2024.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void initTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),0);
//        System.out.println(service.getMachines());
        List<Machine> machines=service.getMachines();
        assertEquals(4,machines.size());

        assertEquals(94,machines.get(0).getAxShift());
        assertEquals(34,machines.get(0).getAyShift());
        assertEquals(8400,machines.get(0).getxTarget());
        assertEquals(5400,machines.get(0).getyTarget());

        assertEquals(27,machines.get(machines.size()-1).getBxShift());
        assertEquals(71,machines.get(machines.size()-1).getByShift());
        assertEquals(18641,machines.get(machines.size()-1).getxTarget());
        assertEquals(10279,machines.get(machines.size()-1).getyTarget());

    }
    @Test
    void calculateTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),0);
        service.calculate();
//        System.out.println(service.getMachines());
        assertEquals(480,service.getTotalCost());
    }
    @Test
    void calculateWithProblemDataTest(){
        ReadData readData=new ReadData("input.txt");
        Service service=new Service(readData.getOutput(),0);
        service.calculate();
//        System.out.println(service.getMachines());
        assertEquals(30973,service.getTotalCost());
    }

    @Test
    void calculatePart2Test(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),10000000000000L);
        service.calculate();
        assertEquals(875318608908L,service.getTotalCost());
    }

    @Test
    void calculatePart2WithProblemDataTest(){
        ReadData readData=new ReadData("input.txt");
        Service service=new Service(readData.getOutput(),10000000000000L);
        service.calculate();
        assertEquals(95688837203288L,service.getTotalCost());
    }

}