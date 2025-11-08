package tzjanosi.y2017.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromenadeTest {
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

    @Test
    void execViaRepeatedExecTest() {
        ReadData readData = new ReadData("testInput.txt");
        Promenade promenade = new Promenade(5, readData.getOutput().get(0));
        assertEquals("baedc", promenade.repeatedExec(1));
    }

    @Test
    void execViaRepeatedExecProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Promenade promenade = new Promenade(16, readData.getOutput().get(0));
        assertEquals("kpfonjglcibaedhm", promenade.repeatedExec(1));
    }

    @Test
    void repeatedExecTest() {
        ReadData readData = new ReadData("testInput.txt");
        Promenade promenade = new Promenade(5, readData.getOutput().get(0));
        assertEquals("ceadb", promenade.repeatedExec(2));
    }

    @Test
    void findPeriodTest() {
        ReadData readData = new ReadData("input.txt");
        Promenade promenade = new Promenade(16, readData.getOutput().get(0));
        assertEquals(42, promenade.findPeriod(100_000));
    }

    @Test
    void repeatedPeriodWithExecProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Promenade promenade = new Promenade(16, readData.getOutput().get(0));
        assertEquals("abcdefghijklmnop", promenade.repeatedExec(42));
    }

    @Test
    void repeatedLenientExecProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Promenade promenade = new Promenade(16, readData.getOutput().get(0));
        assertEquals("odiabmplhfgjcekn", promenade.repeatedLenientExec(1_000_000_000));
    }


//    @Test
//    void sequenceTest() {
//        ReadData readData = new ReadData("testInput2.txt");
//        Promenade promenade = new Promenade(16, readData.getOutput().get(0));
//        assertEquals("kpfonjglcibaedhm", promenade.repeatedExec(1));
//    }

}