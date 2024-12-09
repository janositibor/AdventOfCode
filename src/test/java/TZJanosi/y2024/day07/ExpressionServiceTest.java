package TZJanosi.y2024.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionServiceTest {
    @Test
    void expressionServiceTest(){
        ReadData readData= new ReadData("testInput.txt");
        ExpressionService expressionService=new ExpressionService(readData.getExpectedResults(),readData.getOperands());
        assertEquals(11387,expressionService.calculate());
    }
    @Test
    void expressionServiceWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        ExpressionService expressionService=new ExpressionService(readData.getExpectedResults(),readData.getOperands());
//        System.out.println(expressionService.calculate());
        assertEquals(146111650210682L,expressionService.calculate());
    }

}