package tzjanosi.y2017.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpinlockTest {
    @Test
    void createTest() {
        Spinlock spinlock = new Spinlock();
        assertEquals(638, spinlock.create(3));
    }

    @Test
    void createProblemDataTest() {
        Spinlock spinlock = new Spinlock();
        assertEquals(1487, spinlock.create(367));
    }

}