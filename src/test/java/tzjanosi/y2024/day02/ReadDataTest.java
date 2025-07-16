package tzjanosi.y2024.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadDataTest {
    @Test
    void readDataTest(){
        ReadData readData= new ReadData("testInput.txt");
//        readData.read();
        List<List<Integer>> result=readData.getList();
        assertEquals(3, result.size());
        assertThat(result.get(0)).containsExactly(75,78,81,82,80);
        assertThat(result.get(1))
                .hasSize(8)
                .contains(new Integer[]{14,28});
        assertThat(result.get(2)).containsExactly(61,63,64,66,68,70,73,77);
    }
}