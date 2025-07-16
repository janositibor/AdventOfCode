package tzjanosi_temp.y2024.day06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GuardPathTest {

    @Test
    void initTest(){
        ReadData readData = new ReadData("testMap.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        GuardPath guardPath=new GuardPath(inputMatrix);
//        System.out.println(Arrays.toString(guardPath.getCoordinates()));
        assertEquals("[6, 4]",Arrays.toString(guardPath.getCoordinates()));

    }
    @Test
    void findPathTest(){
        ReadData readData = new ReadData("testMap.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        GuardPath guardPath=new GuardPath(inputMatrix);
//        System.out.println(Arrays.toString(guardPath.getCoordinates()));
//        System.out.println(Arrays.deepToString(guardPath.getMatrixMap()));
        guardPath.findPath(false);
//        System.out.println(Arrays.deepToString(guardPath.getMatrixMap()));
//        System.out.println(guardPath.countVisitedPlaces());
        assertEquals(41,guardPath.countVisitedPlaces());
    }

    @Test
    void findPathWithProblemDataTest(){
        ReadData readData = new ReadData("map.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        GuardPath guardPath=new GuardPath(inputMatrix);
//        System.out.println(Arrays.toString(guardPath.getCoordinates()));
//        System.out.println(Arrays.deepToString(guardPath.getMatrixMap()));
        guardPath.findPath(false);
//        System.out.println(Arrays.deepToString(guardPath.getMatrixMap()));
//        System.out.println(guardPath.countVisitedPlaces());
        assertEquals(5067,guardPath.countVisitedPlaces());
    }


}