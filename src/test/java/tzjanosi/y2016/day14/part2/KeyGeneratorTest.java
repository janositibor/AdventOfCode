package tzjanosi.y2016.day14.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyGeneratorTest {
    @Test
    void generateStretchedMd5HashForIndexTest() {
        KeyGenerator keyGenerator = new KeyGenerator("abc");
        assertEquals(true, keyGenerator.generateStretchedMd5HashForIndex(5).contains("222"));
        assertEquals(true, keyGenerator.generateStretchedMd5HashForIndex(10).contains("eee"));
        assertEquals(true, keyGenerator.generateStretchedMd5HashForIndex(89).contains("eeeee"));
    }

    @Test
    void foundKeysTest() {
        KeyGenerator keyGenerator = new KeyGenerator("abc");
        assertEquals(22551, keyGenerator.foundKeys());
    }

    @Test
    void foundKeysProblemDataTest() {
        KeyGenerator keyGenerator = new KeyGenerator("zpqevtbw");
        assertEquals(22423, keyGenerator.foundKeys());
    }
}