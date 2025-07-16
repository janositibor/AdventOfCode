package tzjanosi.y2015.day18.part2;

import java.util.Objects;

public class Bulb {
    private boolean on;

    public Bulb(boolean on) {
        this.on = on;
    }

    public Bulb() {
    }


    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public void toggle() {
        on = !on;
    }

    public boolean isOn() {
        return on;
    }

    @Override
    public String toString() {
        return "Bulb{" +
                "on=" + on +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bulb bulb = (Bulb) o;
        return on == bulb.on;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(on);
    }
}
