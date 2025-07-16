package tzjanosi_temp.y2015.day16;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InvestigationTest {
    @Test
    void build() {
        ReadData readData = new ReadData("input.txt");
        Investigation investigation = new Investigation(readData.getOutput());
        assertThat(investigation.getCriteria())
                .hasSize(10)
                .contains(Map.entry("children", Optional.of(3)),
                        Map.entry("pomeranians", Optional.of(3)),
                        Map.entry("vizslas", Optional.of(0)),
                        Map.entry("perfumes", Optional.of(1)));
        assertThat(investigation.getSues())
                .hasSize(500)
                .extracting(Sue::getId)
                .contains(1, 500);
        assertThat(investigation.getSues().get(1))
                .hasFieldOrPropertyWithValue("id", 2);
        assertThat(investigation.getSues().get(1).getProperties())
                .hasSize(10)
                .contains(Map.entry("children", Optional.of(3)),
                        Map.entry("akitas", Optional.of(9)),
                        Map.entry("samoyeds", Optional.of(9)));
        assertThat(investigation.getSues().get(499).getProperties())
                .hasSize(10)
                .contains(Map.entry("pomeranians", Optional.of(10)),
                        Map.entry("cats", Optional.of(3)),
                        Map.entry("vizslas", Optional.of(5)));
    }

    @Test
    void filter() {
        ReadData readData = new ReadData("input.txt");
        Investigation investigation = new Investigation(readData.getOutput());
        assertEquals(373, investigation.filterPart1());
    }

    @Test
    void filterPart2() {
        ReadData readData = new ReadData("input.txt");
        Investigation investigation = new Investigation(readData.getOutput());
        assertEquals(260, investigation.filterPart2());
    }

}