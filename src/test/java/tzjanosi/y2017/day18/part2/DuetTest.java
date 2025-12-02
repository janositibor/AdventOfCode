package tzjanosi.y2017.day18.part2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DuetTest {
    @Test
    void init() {
        ReadData readData = new ReadData("input.txt");
        Duet duet = new Duet(readData.getOutput());

        assertSame(duet.getNumbersTo0(), duet.getSolo0().getInputNumbers());
        assertSame(duet.getNumbersTo1(), duet.getSolo0().getOutputNumbers());
        assertSame(duet.getNumbersTo0(), duet.getSolo1().getOutputNumbers());
        assertSame(duet.getNumbersTo1(), duet.getSolo1().getInputNumbers());

//        assertNotSame(duet.getRunFlagTo0(), duet.getRunFlagTo1());
//        assertSame(duet.getRunFlagTo0(), duet.getSolo0().getConnection().getInputFlagRun());
//        assertNotSame(duet.getRunFlagTo0(), duet.getSolo0().getConnection().getOutputFlagRun());
//        assertSame(duet.getRunFlagTo1(), duet.getSolo0().getConnection().getOutputFlagRun());
//        assertSame(duet.getRunFlagTo1(), duet.getSolo1().getConnection().getInputFlagRun());
//        assertSame(duet.getRunFlagTo0(), duet.getSolo1().getConnection().getOutputFlagRun());
//        assertSame(duet.getSolo0().getConnection().getInputFlagRun(), duet.getSolo1().getConnection().getOutputFlagRun());
//        assertSame(duet.getSolo1().getConnection().getInputFlagRun(), duet.getSolo0().getConnection().getOutputFlagRun());
//
//        assertNotSame(duet.getResultFrom0(), duet.getResultFrom1());
//        assertSame(duet.getResultFrom0(), duet.getSolo0().getResultValue());
//        assertSame(duet.getResultFrom1(), duet.getSolo1().getResultValue());

        assertThat(duet.getSolo0().getRegisters())
                .hasSize(6)
                .contains(new Register("one", 1L))
                .contains(new Register("p", 0L));

        assertThat(duet.getSolo1().getRegisters())
                .hasSize(6)
                .contains(new Register("one", 1L))
                .contains(new Register("p", 1L));

    }

    @Test
    void execTest() {
        ReadData readData = new ReadData("testInput2.txt");
        Duet duet = new Duet(readData.getOutput());
        assertEquals(3, duet.exec());
    }

    @Test
    void execProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Duet duet = new Duet(readData.getOutput());
        assertEquals(7366, duet.exec());
    }

}