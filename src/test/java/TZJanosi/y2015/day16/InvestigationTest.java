package TZJanosi.y2015.day16;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InvestigationTest {
    @Test
    void build() {
        ReadData readData = new ReadData("input.txt");
        Investigation investigation = new Investigation(readData.getOutput());
        assertThat(investigation.getCriteria())
                .hasSize(10)
                .contains(Map.entry("children", 3),
                        Map.entry("pomeranians", 3),
                        Map.entry("vizslas", 0),
                        Map.entry("perfumes", 1));
        assertThat(investigation.getSues())
                .hasSize(500)
                .extracting(Sue::getId)
                .contains(1, 500);
        assertThat(investigation.getSues().get(1))
                .hasFieldOrPropertyWithValue("id", 2);
        assertThat(investigation.getSues().get(1).getProperties())
                .hasSize(10)
                .contains(Map.entry("children", 3),
                        Map.entry("akitas", 9),
                        Map.entry("samoyeds", 9));
        assertThat(investigation.getSues().get(499).getProperties())
                .hasSize(10)
                .contains(Map.entry("pomeranians", 10),
                        Map.entry("cats", 3),
                        Map.entry("vizslas", 5));
    }

    @Test
    void filter() {
        ReadData readData = new ReadData("input.txt");
        Investigation investigation = new Investigation(readData.getOutput());
        assertEquals(373, investigation.filter());
    }

}