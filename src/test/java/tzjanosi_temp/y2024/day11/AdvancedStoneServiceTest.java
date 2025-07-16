package tzjanosi_temp.y2024.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvancedStoneServiceTest {
    @Test
    void stoneService1Test(){
        StoneService stoneService=new StoneService(6,"125 17");
        stoneService.advancedCalculation();
        assertEquals(22,stoneService.getTotalNumberOfStones());
    }

    @Test
    void stoneService2Test(){
        StoneService stoneService=new StoneService(25,"125 17");
        stoneService.advancedCalculation();
//        System.out.println(stoneService.getNumberOfStones());
        assertEquals(55312,stoneService.getTotalNumberOfStones());
    }

    @Test
    void stoneServiceWithProblemDataTest(){
        StoneService stoneService=new StoneService(25,"64599 31 674832 2659361 1 0 8867 321");
        stoneService.advancedCalculation();
//        System.out.println(stoneService.getNumberOfStones());
        assertEquals(199986,stoneService.getTotalNumberOfStones());
    }

    @Test
    void CalculationWithProblemData50Test() {
        StoneService stoneService=new StoneService(50,"64599 31 674832 2659361 1 0 8867 321");
        stoneService.advancedCalculation();
        assertEquals(6855170414L, stoneService.getTotalNumberOfStones());
    }
    @Test
    void CalculationWithProblemData75Test() {
        StoneService stoneService=new StoneService(75,"64599 31 674832 2659361 1 0 8867 321");
        stoneService.advancedCalculation();
        System.out.println(stoneService.getTotalNumberOfStones());
        assertEquals(236804088748754L, stoneService.getTotalNumberOfStones());
    }
}
