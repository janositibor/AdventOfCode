package tzjanosi.y2016.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void findTheWinnerTest() {
        Game game = new Game(5);
        assertEquals(3, game.findTheWinner());
    }

    @Test
    void findTheWinnerProgramDataTest() {
        Game game = new Game(3014387);
        assertEquals(1834471, game.findTheWinner());
    }
}