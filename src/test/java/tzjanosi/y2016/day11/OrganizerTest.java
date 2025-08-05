package tzjanosi.y2016.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizerTest {
    @Test
    void findBestWay() {
        ReadData readData = new ReadData("testInput.txt");
        Organizer organizer = new Organizer(readData.getOutput());
        assertEquals(11, organizer.findBestWay());
    }

    @Test
    void findBestWayProblemData() {
        ReadData readData = new ReadData("input.txt");
        Organizer organizer = new Organizer(readData.getOutput());
        assertEquals(31, organizer.findBestWay());
    }

    @Test
    void findBestWayProblemDataPart2() {
        ReadData readData = new ReadData("inputPart2.txt");
        Organizer organizer = new Organizer(readData.getOutput());
        assertEquals(55, organizer.findBestWay());
    }

}