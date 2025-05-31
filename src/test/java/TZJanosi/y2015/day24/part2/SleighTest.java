package TZJanosi.y2015.day24.part2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SleighTest {
    @Test
    void invalidPackagesWeightTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Sleigh(Arrays.asList(1, 2, 3, 4), 3));
        assertEquals("No possibility to divide into 3 equal groups. Total weight is: 10", e.getMessage());
    }

    @Test
    void weightLimitTest() {
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 2, 3, 4, 5, 6, 27), 3);
        assertEquals(16, sleigh.getWeightLimit());
    }

    @Test
    void replaceNextElementTest() {
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 2, 3, 4, 5, 6, 27), 3);
        assertEquals(0, sleigh.getSlots().get(1).getNumberOfPackages());
        Sleigh altered = sleigh.replaceNextElementIntoASlot(1);
        assertEquals(1, sleigh.getSlots().get(1).getNumberOfPackages());
        assertEquals(1, altered.getSlots().get(1).getNumberOfPackages());
    }

}