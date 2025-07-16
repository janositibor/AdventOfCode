package tzjanosi_temp.y2024.day25;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrganizerTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        Organizer organizer = new Organizer(readData.getOutput());
        assertThat(organizer.getKeys())
                .hasSize(2)
                .extracting(Key::getHeights)
                .containsExactly(List.of(0, 5, 3, 4, 3),
                        List.of(1, 2, 0, 5, 3));
        assertThat(organizer.getLocks())
                .hasSize(3)
                .extracting(Lock::getHeights)
                .containsExactly(List.of(5, 0, 2, 1, 3),
                        List.of(4, 3, 4, 0, 2),
                        List.of(3, 0, 2, 0, 1)
                );
    }

    @Test
    void counterWithTestData() {
        ReadData readData = new ReadData("testInput.txt");
        Organizer organizer = new Organizer(readData.getOutput());
        assertEquals(3, organizer.countOfCompatiblePairs());
    }

    @Test
    void counterWithProblemData() {
        ReadData readData = new ReadData("input.txt");
        Organizer organizer = new Organizer(readData.getOutput());
        assertEquals(3146, organizer.countOfCompatiblePairs());
    }
}