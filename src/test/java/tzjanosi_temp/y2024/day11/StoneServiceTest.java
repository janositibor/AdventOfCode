package tzjanosi_temp.y2024.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoneServiceTest {
    @Test
    void stoneService1Test(){
        StoneService stoneService=new StoneService(6,"125 17");
        stoneService.calculation();
//        System.out.println(stoneService.getNumberOfStones());
        assertEquals(22,stoneService.getNumberOfStones());
    }

    @Test
    void stoneService2Test(){
        StoneService stoneService=new StoneService(25,"125 17");
        stoneService.calculation();
//        System.out.println(stoneService.getNumberOfStones());
        assertEquals(55312,stoneService.getNumberOfStones());
    }

    @Test
    void stoneServiceWithProblemDataTest(){
        StoneService stoneService=new StoneService(25,"64599 31 674832 2659361 1 0 8867 321");
        stoneService.calculation();
//        System.out.println(stoneService.getNumberOfStones());
        assertEquals(199986,stoneService.getNumberOfStones());
    }

}