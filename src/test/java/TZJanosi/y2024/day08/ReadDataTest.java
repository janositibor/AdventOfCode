package TZJanosi.y2024.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadDataTest {
    @Test
    void readDataTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<String> result=readData.getList();
        assertEquals(12,result.size());
        assertEquals("............",result.get(0));
        assertEquals("......A.....",result.get(5));
        assertEquals("............",result.get(result.size()-1));
    }

}