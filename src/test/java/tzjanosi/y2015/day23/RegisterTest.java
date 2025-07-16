package tzjanosi.y2015.day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    Register register;

    @BeforeEach
    void init() {
        register = new Register("a", 0);
        assertEquals(0, register.getValue());
    }

    @Test
    void inc() {
        assertEquals(1, register.inc());
        assertEquals(1, register.getValue());
    }

    @Test
    void hlf() {
        register.inc();
        assertEquals(1, register.hlf());
        assertEquals(0, register.getValue());
        register.inc();
        register.inc();
        register.inc();
        register.inc();
        register.hlf();
        assertEquals(2, register.getValue());
        register.inc();
        register.hlf();
        assertEquals(1, register.getValue());
    }

    @Test
    void tpl() {
        register.inc();
        assertEquals(1, register.tpl());
        assertEquals(3, register.getValue());
    }

    @Test
    void jmp() {
        assertEquals(-1984, register.jmp(-1984));
    }

    @Test
    void jie() {
        register.inc();
        assertEquals(1, register.jie(1984));
        register.inc();
        register.inc();
        assertEquals(1, register.jie(1984));
        register.inc();
        assertEquals(1984, register.jie(1984));
        register.inc();
        assertEquals(1, register.jie(1984));
    }

    @Test
    void jio() {
        assertEquals(1, register.jio(14));
        register.inc();
        assertEquals(14, register.jio(14));
        register.inc();
        assertEquals(1, register.jio(14));
        register.inc();
        assertEquals(1, register.jio(14));
        register.inc();
        assertEquals(1, register.jio(14));
    }

}