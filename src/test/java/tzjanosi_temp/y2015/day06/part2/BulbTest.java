package tzjanosi_temp.y2015.day06.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulbTest {
    @Test
    void switchTest() {
        Bulb bulb = new Bulb();

        assertEquals(0, bulb.getIntensity());
        bulb.turnOn();
        assertEquals(1, bulb.getIntensity());
        bulb.toggle();
        assertEquals(3, bulb.getIntensity());
        bulb.turnOff();
        assertEquals(2, bulb.getIntensity());
        bulb.turnOff();
        bulb.turnOff();
        bulb.turnOff();
        bulb.turnOff();
        bulb.turnOff();
        assertEquals(0, bulb.getIntensity());
    }

}