package tzjanosi.y2017.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromenadeTest {
//    @Test
//    void initTest(){
//        ReadData readData=new ReadData("input.txt");
//        Promenade promenade=new Promenade(16, readData.getOutput().get(0));
//        promenade.check();
//    }

    @Test
    void execTest() {
        ReadData readData = new ReadData("testInput.txt");
        Promenade promenade = new Promenade(5, readData.getOutput().get(0));
        assertEquals("baedc", promenade.exec());
    }

    @Test
    void execProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Promenade promenade = new Promenade(16, readData.getOutput().get(0));
        assertEquals("kpfonjglcibaedhm", promenade.exec());
    }

}