package tzjanosi.y2016.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinerTest {
    @Test
    void test() {
        Miner miner = new Miner("abc");
        miner.buildPassWord();
        assertEquals("18f47a30", miner.getPassword());
    }

    @Test
    void problemData() {
        Miner miner = new Miner("wtnhxymk");
        miner.buildPassWord();
        assertEquals("2414bc77", miner.getPassword());
    }

    @Test
    void testPart2() {
        Miner miner = new Miner("abc");
        miner.buildPassWordPart2();
        assertEquals("05ace8e3", miner.getPassword());
    }

    @Test
    void problemDataPart2() {
        Miner miner = new Miner("wtnhxymk");
        miner.buildPassWordPart2();
        assertEquals("437e60fc", miner.getPassword());
    }

}