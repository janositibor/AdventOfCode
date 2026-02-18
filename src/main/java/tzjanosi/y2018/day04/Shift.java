package tzjanosi.y2018.day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Shift {
    private String date;
    private int guard;
    private List<Sleep> sleeps = new ArrayList<>();

    public Shift(String date, int guard) {
        this.date = date;
        this.guard = guard;
    }

    public int sleepingDuration() {
        return sleeps.stream()
                .mapToInt(Sleep::duration).sum();
    }

    public List<Integer> sleepingMinutes() {
        return sleeps.stream().map(Sleep::sleepingMinutes).flatMap(Collection::stream).toList();
    }

    public void addSleep(Sleep sleep) {
        sleeps.add(sleep);
    }

    public int getGuard() {
        return guard;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "date='" + date + '\'' +
                ", guard=" + guard +
                ", sleeps=" + sleeps +
                '}';
    }
}
