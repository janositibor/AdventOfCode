package TZJanosi.y2015.day14;

import java.util.Objects;

public class Deer {
    private String name;
    private int speed;
    private int flightDuration;
    private int period;
    private int distanceInOnePeriod;

    public Deer(String name, int speed, int flightDuration, int period) {
        this.name = name;
        this.speed = speed;
        this.flightDuration = flightDuration;
        this.period = period;
        distanceInOnePeriod = speed * flightDuration;
    }

    public int distanceInTime(int time) {
        int numberOfTotalPeriods = time / period;
        int remainingTime = time % period;
        int remainingFlyTime = Math.min(remainingTime, flightDuration);
        return numberOfTotalPeriods * distanceInOnePeriod + remainingFlyTime * speed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Deer deer = (Deer) o;
        return speed == deer.speed && flightDuration == deer.flightDuration && period == deer.period && distanceInOnePeriod == deer.distanceInOnePeriod && Objects.equals(name, deer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speed, flightDuration, period, distanceInOnePeriod);
    }

    @Override
    public String toString() {
        return "Deer{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", flightDuration=" + flightDuration +
                ", period=" + period +
                ", distanceInOnePeriod=" + distanceInOnePeriod +
                "}\n";
    }
}
