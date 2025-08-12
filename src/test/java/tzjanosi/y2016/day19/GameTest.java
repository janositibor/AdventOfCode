package tzjanosi.y2016.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void findTheWinnerPart1Test() {
        Game game = new Game(5);
        assertEquals(3, game.findTheWinnerPart1());
    }

    @Test
    void findTheWinnerProgramDataPart1Test() {
        Game game = new Game(3014387);
        assertEquals(1834471, game.findTheWinnerPart1());
    }

    @Test
    void findTheWinnerPart2Test() {
        Game game = new Game(5);
        assertEquals(2, game.findTheWinnerPart2());
    }

    @Test
    void findTheWinnerPart2Test2() {
        Game game = new Game(10);
        assertEquals(1, game.findTheWinnerPart2());
        assertEquals(1, game.findTheWinnerPart2());
    }

    @Test
    void findTheWinnerPart2Test3() {
        Game game = new Game(11);
        assertEquals(2, game.findTheWinnerPart2());
    }

    @Test
    void findTheWinnerProgramDataPart2Test() {
        Game game = new Game(3014387);
        assertEquals(1420064, game.findTheWinnerPart2());
    }
}