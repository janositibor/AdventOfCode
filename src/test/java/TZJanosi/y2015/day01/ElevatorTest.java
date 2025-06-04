package TZJanosi.y2015.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {
    @Test
    void calculateFloor() {
        ReadData readData = new ReadData("input.txt");
        Elevator elevator = new Elevator(readData.getOutput().get(0));

        assertEquals(280, elevator.calculateFloor());
    }

}