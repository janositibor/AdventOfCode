package tzjanosi.y2016.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyGeneratorTest {
    @Test
    void generateMd5HashForIndexTest() {
        KeyGenerator keyGenerator = new KeyGenerator("abc");
        assertEquals(true, keyGenerator.generateMd5HashForIndex(18).contains("888"));
        assertEquals(true, keyGenerator.generateMd5HashForIndex(39).contains("eee"));
        assertEquals(true, keyGenerator.generateMd5HashForIndex(816).contains("eeeee"));
    }

    @Test
    void findFirstKeyFromIndexTest() {
        KeyGenerator keyGenerator = new KeyGenerator("abc");
        assertEquals(39, keyGenerator.findFirstKeyFromIndex(0));
    }

    @Test
    void foundKeysProblemDataTest() {
        KeyGenerator keyGenerator = new KeyGenerator("zpqevtbw");
        assertEquals(16106, keyGenerator.foundKeys());
    }

}