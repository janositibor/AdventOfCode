package tzjanosi.y2016.day10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrganizerTest {
    @Test
    void run() {
        ReadData readData = new ReadData("testInput.txt");
        Organizer organizer = new Organizer(readData.getOutput(), Arrays.asList(2, 5));
        assertEquals(2, organizer.run());
    }

    @Test
    void runProblemData() {
        ReadData readData = new ReadData("input.txt");
        Organizer organizer = new Organizer(readData.getOutput(), Arrays.asList(17, 61));
        assertEquals(147, organizer.run());
    }

}