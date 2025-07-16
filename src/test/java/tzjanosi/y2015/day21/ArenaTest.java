package tzjanosi.y2015.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    @Test
    void findWinnerSetsForPart1() {
        Arena arena = new Arena();
        assertEquals(78, arena.findCheapestWinner());
    }

    @Test
    void findLoserSetsForPart2() {
        Arena arena = new Arena();
        assertEquals(78, arena.findCheapestWinner());
        assertEquals(148, arena.findMostExpensiveLoser());
    }
}