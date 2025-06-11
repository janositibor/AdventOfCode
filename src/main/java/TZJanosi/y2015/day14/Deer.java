package TZJanosi.y2015.day14;

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
