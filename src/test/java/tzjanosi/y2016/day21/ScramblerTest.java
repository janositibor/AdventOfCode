package tzjanosi.y2016.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScramblerTest {
    @Test
    void createTest() {
        ReadData readData = new ReadData("testInput.txt");
        Scrambler scrambler = new Scrambler(readData.getOutput());
        assertThat(scrambler.getOperations())
                .containsExactly(
                        new Operation(OperationType.SWAP_POSITION, 4, 0),
                        new Operation(OperationType.SWAP_LETTER, 100, 98),
                        new Operation(OperationType.REVERSE_POSITIONS, 0, 4),
                        new Operation(OperationType.ROTATE_LEFT, 1, 0),
                        new Operation(OperationType.MOVE_POSITION, 1, 4),
                        new Operation(OperationType.MOVE_POSITION, 3, 0),
                        new Operation(OperationType.ROTATE_BASED_ON_POSITION, 98, 0),
                        new Operation(OperationType.ROTATE_BASED_ON_POSITION, 100, 0)
                );
    }

    @Test
    void createPasswordTest() {
        ReadData readData = new ReadData("testInput.txt");
        Scrambler scrambler = new Scrambler(readData.getOutput());
        assertEquals("decab", scrambler.createPassword("abcde"));
    }

    @Test
    void createPasswordProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Scrambler scrambler = new Scrambler(readData.getOutput());
        assertEquals("dbfgaehc", scrambler.createPassword("abcdefgh"));
    }

    @Test
    void decryptPasswordPart1ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Scrambler scrambler = new Scrambler(readData.getOutput());
        assertEquals("abcdefgh", scrambler.decryptPassword("dbfgaehc"));
//        assertEquals("", scrambler.decryptPassword("fbgdceah"));
    }

    @Test
    void decryptPasswordPart2ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Scrambler scrambler = new Scrambler(readData.getOutput());
        assertEquals("aghfcdeb", scrambler.decryptPassword("fbgdceah"));
    }
}