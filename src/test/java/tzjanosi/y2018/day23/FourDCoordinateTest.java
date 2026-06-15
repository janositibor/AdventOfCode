package tzjanosi.y2018.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourDCoordinateTest {
    @Test
    void basicTest() {
        Coordinate coordinate = new Coordinate(10, 12, 12);
        FourDCoordinate fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());
        fourDCoordinate = new FourDCoordinate(34, 10, 10, 14);
        assertEquals(fourDCoordinate, new FourDCoordinate(coordinate));

        coordinate = new Coordinate(12, 14, 12);
        fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());

        coordinate = new Coordinate(16, 12, 12);
        fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());

        coordinate = new Coordinate(14, 14, 14);
        fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());

        coordinate = new Coordinate(50, 50, 50);
        fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());

        coordinate = new Coordinate(10, 10, 10);
        fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());

        coordinate = new Coordinate(12, 12, 12);
        fourDCoordinate = new FourDCoordinate(coordinate);
        assertEquals(coordinate, fourDCoordinate.transformToCoordinate());

    }

}