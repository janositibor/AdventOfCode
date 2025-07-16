package tzjanosi.y2015.day06.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulbTest {
    @Test
    void switchTest() {
        Bulb bulb = new Bulb();

        assertEquals(false, bulb.isOn());
        bulb.turnOn();
        assertEquals(true, bulb.isOn());
        bulb.toggle();
        assertEquals(false, bulb.isOn());
        bulb.toggle();
        assertEquals(true, bulb.isOn());
        bulb.turnOff();
        assertEquals(false, bulb.isOn());
    }

}