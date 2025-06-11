package TZJanosi.y2015.day13;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TablePlannerTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        TablePlanner tablePlanner = new TablePlanner(readData.getOutput());
        assertTrue(tablePlanner.getFoundPlace().isEmpty());
        assertThat(tablePlanner.getLookForPlace())
                .hasSize(4)
                .containsOnly("Alice", "Bob", "Carol", "David");
        assertThat(tablePlanner.getCouples())
                .hasSize(6);
        assertEquals(0, tablePlanner.getHappiness());
        assertEquals(137, tablePlanner.findCouple("Bob", "Alice").getHappiness());
        assertEquals(-70, tablePlanner.findCouple("Bob", "David").getHappiness());
    }
}