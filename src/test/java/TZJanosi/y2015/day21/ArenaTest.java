package TZJanosi.y2015.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    @Test
    void findWinnerSets() {
        Arena arena = new Arena();
        assertEquals(78, arena.findCheapest());
    }
}