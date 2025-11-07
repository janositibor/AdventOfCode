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
    }

    @Test
    public void countMatchProblemDataTest() {
        Duel duel = new Duel();
        assertEquals(619, duel.countMatch(40_000_000, 591, 393));
    }

    @Test
    public void pickyCountMatchTest() {
        Duel duel = new Duel();
        assertEquals(0, duel.pickyCountMatch(1055, 65, 8921));
        assertEquals(1, duel.pickyCountMatch(1056, 65, 8921));
        assertEquals(309, duel.pickyCountMatch(5_000_000, 65, 8921));
    }

    @Test
    public void pickyCountMatchProblemDataTest() {
        Duel duel = new Duel();
        assertEquals(290, duel.pickyCountMatch(5_000_000, 591, 393));
    }

}