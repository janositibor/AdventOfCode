package tzjanosi.y2019.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void counterTest() {
        Service service = new Service();
        assertEquals(460, service.counter());
    }

}