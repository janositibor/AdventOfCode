package TZJanosi.y2024.day23;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LanPartyTest {
    @Test
    void buildLanparty() {
        ReadData readData = new ReadData("testInput.txt");
        LanParty lanParty = new LanParty(readData.getOutput());
        assertThat(lanParty.getComputers())
                .hasSize(16)
                .contains("kh", "tc", "qp", "de", "cg", "ka", "co", "yn", "wq");
        assertThat(lanParty.getConnections())
                .hasSize(32)
                .contains(new Connection("kh", "tc"),
                        new Connection("kh", "qp"),
                        new Connection("td", "yn")
                );

    }

    @Test
    void findGroups() {
        ReadData readData = new ReadData("testInput.txt");
        LanParty lanParty = new LanParty(readData.getOutput());
        lanParty.findGroups();
        assertThat(lanParty.getGroups())
                .hasSize(12)
                .containsOnly(new Group("aq", "cg", "yn"),
                        new Group("aq", "vc", "wq"),
                        new Group("co", "de", "ka"),
                        new Group("co", "de", "ta"),
                        new Group("co", "ka", "ta"),
                        new Group("de", "ka", "ta"),
                        new Group("kh", "qp", "ub"),
                        new Group("qp", "td", "wh"),
                        new Group("tb", "vc", "wq"),
                        new Group("tc", "td", "wh"),
                        new Group("td", "wh", "yn"),
                        new Group("ub", "vc", "wq")
                );
    }

    @Test
    void countOfGroupWithPrefixT() {
        ReadData readData = new ReadData("testInput.txt");
        LanParty lanParty = new LanParty(readData.getOutput());
        lanParty.findGroups();
        assertEquals(7, lanParty.countOfGroupWithPrefixT());
    }

    @Test
    void countOfGroupInProblemDataWithPrefixT() {
        ReadData readData = new ReadData("input.txt");
        LanParty lanParty = new LanParty(readData.getOutput());
        lanParty.findGroups();
        assertEquals(1200, lanParty.countOfGroupWithPrefixT());
    }

}