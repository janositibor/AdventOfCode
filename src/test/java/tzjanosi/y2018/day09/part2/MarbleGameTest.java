package tzjanosi.y2018.day09.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarbleGameTest {
    @Test
    void findWinnerScoreTest() {
        MarbleGame marbleGame = new MarbleGame(9, 25);
        assertEquals(32, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreTest2() {
        MarbleGame marbleGame = new MarbleGame(10, 1618);
        assertEquals(8317, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreTest3() {
        MarbleGame marbleGame = new MarbleGame(13, 7999);
        assertEquals(146373, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreTest4() {
        MarbleGame marbleGame = new MarbleGame(17, 1104);
        assertEquals(2764, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreTest5() {
        MarbleGame marbleGame = new MarbleGame(21, 6111);
        assertEquals(54718, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreTest6() {
        MarbleGame marbleGame = new MarbleGame(30, 5807);
        assertEquals(37305, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreProblemDataTest() {
        MarbleGame marbleGame = new MarbleGame(458, 71307);
        assertEquals(398048, marbleGame.findWinnerScore());
    }

    @Test
    void findWinnerScoreProblemDataPart2Test() {
        MarbleGame marbleGame = new MarbleGame(458, 7130700);
        assertEquals(3180373421L, marbleGame.findWinnerScore());
    }

}