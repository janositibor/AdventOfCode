package TZJanosi.y2024.day08;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AntennaServiceTest {
    @Test
    void antennaServiceInitTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        System.out.println(antennaService.getAntennaMap().getAntennaMap());
    }

    @Test
    void antinodesTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes(false);
//        System.out.println(antennaService.countAntinodes());
        assertEquals(14,antennaService.countAntinodes());
//        System.out.println(antennaService.getAntennaMap().getLimit());
    }

    @Test
    void antinodesWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes(false);
//        System.out.println(antennaService.countAntinodes());
        assertEquals(348,antennaService.countAntinodes());
    }

    @Test
    void extendedAntinodes2Test(){
        ReadData readData= new ReadData("testInput2.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes(true);
//        System.out.println(antennaService.getAntennaMap().getAntennaMap());
//        System.out.println(antennaService.getAntinodes());
//        System.out.println(antennaService.countAntinodes());
        assertEquals(9,antennaService.countAntinodes());
//        System.out.println(antennaService.getAntennaMap().getLimit());
    }
    @Test
    void extendedAntinodesTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes(true);
//        System.out.println(antennaService.getAntennaMap().getAntennaMap());
//        System.out.println(antennaService.getAntinodes());
//        System.out.println(antennaService.countAntinodes());
        assertEquals(34,antennaService.countAntinodes());
//        System.out.println(antennaService.getAntennaMap().getLimit());
    }

    @Test
    void extendedAntinodesWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes(true);
//        System.out.println(antennaService.getAntennaMap().getAntennaMap());
//        System.out.println(antennaService.getAntinodes());
//        System.out.println(antennaService.countAntinodes());
        assertEquals(1221,antennaService.countAntinodes());
//        System.out.println(antennaService.getAntennaMap().getLimit());
    }

}