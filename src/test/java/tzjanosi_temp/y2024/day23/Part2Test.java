package tzjanosi_temp.y2024.day23;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {
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
    void bigGroupAndPasswordTest() {
        ReadData readData = new ReadData("testInput.txt");
        LanParty lanParty = new LanParty(readData.getOutput());
        lanParty.createBigGroups();
        assertEquals("co,de,ka,ta", lanParty.getPasswordOfBiggerGroup());
    }

    @Test
    void passwordWithProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        LanParty lanParty = new LanParty(readData.getOutput());
        lanParty.createBigGroups();
        assertEquals("ag,gh,hh,iv,jx,nq,oc,qm,rb,sm,vm,wu,zr", lanParty.getPasswordOfBiggerGroup());
    }
}
