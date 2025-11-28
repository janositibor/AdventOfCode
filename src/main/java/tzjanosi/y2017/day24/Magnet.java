package tzjanosi.y2017.day24;

import java.util.List;
import java.util.Objects;

public class Magnet {
    private int pinA;
    private int pinB;

    public Magnet(int pinA, int pinB) {
        this.pinA = pinA;
        this.pinB = pinB;
    }

    public Magnet(Magnet original) {
        this.pinA = original.pinA;
        this.pinB = original.pinB;
    }


    public List<Integer> getPins() {
        return List.of(pinA, pinB);
    }

    public int getFreePin(int occupied) {
        return pinA == occupied ? pinB : pinA;
    }

    public int getStrength() {
        return pinA + pinB;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Magnet magnet = (Magnet) o;
        return pinA == magnet.pinA && pinB == magnet.pinB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinA, pinB);
    }
}
