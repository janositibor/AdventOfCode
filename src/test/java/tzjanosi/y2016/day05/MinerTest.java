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

}