package tzjanosi_temp.y2024.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationServiceTest {
    @Test
    void equationServiceTest(){
        ReadData readData= new ReadData("testInput.txt");
        EquationService equationService=new EquationService(readData.getExpectedResults(),readData.getOperands());
//        System.out.println(equationService.calculate());
        assertEquals(3749,equationService.calculate());
    }

    @Test
    void equationServiceWitProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        EquationService equationService=new EquationService(readData.getExpectedResults(),readData.getOperands());
        System.out.println(equationService.calculate());
        assertEquals(303876485655L,equationService.calculate());
    }

}