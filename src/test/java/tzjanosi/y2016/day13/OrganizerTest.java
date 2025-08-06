package tzjanosi.y2016.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizerTest {
    @Test
    void findShortestWayTest() {
        Organizer organizer = new Organizer(new Coordinate(7, 4), 10);
        assertEquals(11, organizer.findShortestWay());
    }

    @Test
    void findShortestWayProblemDataTest() {
        Organizer organizer = new Organizer(new Coordinate(31, 39), 1364);
        assertEquals(86, organizer.findShortestWay());
    }

    @Test
    void findNumberOfLocationsInDistanceProblemDataTest() {
        Organizer organizer = new Organizer(new Coordinate(31, 39), 1364);
        assertEquals(86, organizer.findShortestWay());
        assertEquals(127, organizer.numberOfLocationsInDistance());
    }

}