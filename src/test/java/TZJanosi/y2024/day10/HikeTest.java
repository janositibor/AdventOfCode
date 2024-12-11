package TZJanosi.y2024.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HikeTest {
//    @Test
//    void initTest(){
//        ReadData readData= new ReadData("testInput.txt");
//        List<List<Integer>> input=readData.getOutput();
//
//        HikeForPathSearch hike=new HikeForPathSearch(input);
//
//        List<List<Integer>> originalMap=hike.getOriginalMap();
//        System.out.println(originalMap);
//        assertEquals(8,originalMap.size());
//        assertEquals(8,originalMap.get(0).size());
//        assertEquals(8,originalMap.get(0).get(0));
//        assertEquals(9,originalMap.get(3).get(4));
//        assertEquals(2,originalMap.get(7).get(7));
//        assertThat(originalMap.get(5)).containsExactly(3,2,0,1,9,0,1,2);
//
//        List<List<Integer>> resultMap=hike.getResultMap();
//        System.out.println(resultMap);
//        assertEquals(8,resultMap.size());
//        assertEquals(8,resultMap.get(0).size());
//        assertEquals(0,resultMap.get(0).get(0));
//        assertEquals(0,resultMap.get(3).get(4));
//        assertEquals(0,resultMap.get(7).get(7));
//        assertThat(resultMap.get(5)).containsExactly(0,0,0,0,0,0,0,0);
//    }

    @Test
    void calculatePathTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<List<Integer>> input=readData.getOutput();

        Hike hike=new Hike(input);
//        System.out.println(hike.getHikeMap());
//        System.out.println(hike.calculatePaths());

        assertEquals(36,hike.calculatePaths());
    }

    @Test
    void calculatePathWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        List<List<Integer>> input=readData.getOutput();

        Hike hike=new Hike(input);
//        System.out.println(hike.getHikeMap());
//        System.out.println(hike.calculatePaths());

        assertEquals(459,hike.calculatePaths());
    }

//    countDistinctPaths

}