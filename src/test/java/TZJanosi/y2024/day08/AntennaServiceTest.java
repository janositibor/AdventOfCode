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
    void AntinodesTest(){
        ReadData readData= new ReadData("testInput.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes();
//        System.out.println(antennaService.countAntinodes());
        assertEquals(14,antennaService.countAntinodes());
//        System.out.println(antennaService.getAntennaMap().getLimit());
    }

    @Test
    void AntinodesWithProblemDataTest(){
        ReadData readData= new ReadData("input.txt");
        List<String> result=readData.getList();
        AntennaService antennaService=new AntennaService();
        antennaService.readInputListOfStrings(result);
        antennaService.setAntinodes();
//        System.out.println(antennaService.countAntinodes());
        assertEquals(348,antennaService.countAntinodes());
    }

}