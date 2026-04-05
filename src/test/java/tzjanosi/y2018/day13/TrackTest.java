package tzjanosi.y2018.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {
    @Test
    void findCrashTest() {
        ReadData readData = new ReadData("testInput.txt");
        Track track = new Track(readData.getOutput());
        assertEquals("7,3", track.findCrash());
    }

    @Test
    void findCrashProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Track track = new Track(readData.getOutput());
        assertEquals("41,17", track.findCrash());
    }

}