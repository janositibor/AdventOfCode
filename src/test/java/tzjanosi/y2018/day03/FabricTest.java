package tzjanosi.y2018.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricTest {
    @Test
    void numberOfConflictingClaimsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Fabric fabric = new Fabric(readData.getOutput());
        assertEquals(4, fabric.areaOfConflictingClaims());
    }

    @Test
    void numberOfConflictingClaimsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Fabric fabric = new Fabric(readData.getOutput());
        assertEquals(105047, fabric.areaOfConflictingClaims());
    }

    @Test
    void idOfTheNonOverlappingRectangleTest() {
        ReadData readData = new ReadData("testInput.txt");
        Fabric fabric = new Fabric(readData.getOutput());
        assertEquals(3, fabric.idOfTheNonOverlappingRectangle());
    }

    @Test
    void idOfTheNonOverlappingRectangleProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Fabric fabric = new Fabric(readData.getOutput());
        assertEquals(658, fabric.idOfTheNonOverlappingRectangle());
    }


}