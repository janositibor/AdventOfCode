package tzjanosi.y2016.day12;

import java.util.Objects;

public class Register {
    private RegisterId id;
    private int value;

    public Register(RegisterId id) {
        this.id = id;
    }

    public void copy(int value) {
        this.value = value;
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public RegisterId getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Register register = (Register) o;
        return id == register.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
