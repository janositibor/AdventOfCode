package TZJanosi.y2024.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoopServiceTest {
    @Test
    void loopServiceTest(){
        ReadData readData = new ReadData("testMap.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        LoopService loopService=new LoopService(inputMatrix);
        loopService.countLoops();
//        System.out.println(loopService.getNumberOfLoops());
        assertEquals(6,loopService.getNumberOfLoops());
    }
    @Test
    void loopServiceWithProblemDataTest(){
        ReadData readData = new ReadData("map.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        LoopService loopService=new LoopService(inputMatrix);
        loopService.countLoops();;
//        System.out.println(loopService.getNumberOfLoops());
        assertEquals(1793,loopService.getNumberOfLoops());
    }

}