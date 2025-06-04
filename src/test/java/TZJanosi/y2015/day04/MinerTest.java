package TZJanosi.y2015.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinerTest {
    @Test
    void testData() {
        Miner miner1 = new Miner("abcdef", "00000");
        assertEquals(609043, miner1.findFirstMatch());
        Miner miner2 = new Miner("pqrstuv", "00000");
        assertEquals(1048970, miner2.findFirstMatch());
    }

    @Test
    void problemData() {
        Miner miner = new Miner("yzbqklnj", "00000");
        assertEquals(282749, miner.findFirstMatch());
    }
//    @Test
//    void problemDataPart2(){
//        Miner miner=new Miner("yzbqklnj","000000");
//        assertEquals(282749,miner.findFirstMatch());
//    }
}