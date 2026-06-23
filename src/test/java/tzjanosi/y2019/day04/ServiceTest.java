package tzjanosi.y2019.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void counterPart1Test() {
        Service service = new Service();
        assertEquals(460, service.counterPart1());
    }

    @Test
    void counterPart2Test() {
        Service service = new Service();
        assertEquals(290, service.counterPart2());
    }

}