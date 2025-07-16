package tzjanosi_temp.y2024.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HikeTest {
    @Test
    void calculatePeakTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<List<Integer>> input=readData.getOutput();
        Hike hike=new Hike(input);
        assertEquals(36,hike.calculatePaths().get("peaks"));
    }

    @Test
    void calculatePeakWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        List<List<Integer>> input=readData.getOutput();
        Hike hike=new Hike(input);
        assertEquals(459,hike.calculatePaths().get("peaks"));
    }

//    countDistinctPaths
    @Test
    void calculatePathTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<List<Integer>> input=readData.getOutput();
        Hike hike=new Hike(input);
        assertEquals(81,hike.calculatePaths().get("paths"));
    }

    @Test
    void calculatePathWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        List<List<Integer>> input=readData.getOutput();
        Hike hike=new Hike(input);
        assertEquals(1034,hike.calculatePaths().get("paths"));
    }
}