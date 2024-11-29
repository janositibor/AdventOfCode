package TZJanosi.y2015.day25;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void cellTest(){
        Cell firstCell=new Cell(Cell.FIRST_NUMBER);
        BigInteger second=firstCell.getValue();
        assertEquals(new BigInteger("31916031"),second);
    }

}