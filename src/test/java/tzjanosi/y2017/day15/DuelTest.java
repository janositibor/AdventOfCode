package tzjanosi.y2017.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuelTest {
    @Test
    public void countMatchTest() {
        Duel duel = new Duel();
        assertEquals(0, duel.countMatch(2, 65, 8921));
        assertEquals(1, duel.countMatch(3, 65, 8921));
        assertEquals(1, duel.countMatch(5, 65, 8921));
        assertEquals(588, duel.countMatch(40_000_000, 65, 8921));
        assertEquals(619, duel.countMatch(40_000_000, 591, 393));
    }

}