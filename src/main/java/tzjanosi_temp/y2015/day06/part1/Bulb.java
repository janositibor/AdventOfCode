package tzjanosi_temp.y2015.day06.part1;

public class Bulb {
    private boolean on;

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


}
