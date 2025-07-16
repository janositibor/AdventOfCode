package tzjanosi_temp.y2015.day06.part2;

public class Bulb {
    private int intensity;

    public void turnOn() {
        intensity++;
    }

    public void turnOff() {
        intensity--;
        if (intensity < 0) {
            intensity = 0;
        }
    }

    public void toggle() {
        intensity += 2;
    }

    public int getIntensity() {
        return intensity;
    }


}
