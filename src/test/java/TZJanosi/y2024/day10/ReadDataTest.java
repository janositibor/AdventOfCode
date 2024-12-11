package TZJanosi.y2024.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadDataTest {
    @Test
    void readDataTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<List<Integer>> result=readData.getOutput();
        System.out.println(result);
        assertEquals(8,result.size());
        assertEquals(8,result.get(0).size());
        assertEquals(8,result.get(0).get(0));
        assertEquals(9,result.get(3).get(4));
        assertEquals(2,result.get(7).get(7));
        assertThat(result.get(5)).containsExactly(3,2,0,1,9,0,1,2);
    }

}