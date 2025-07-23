package tzjanosi.y2016.day10;

import java.util.ArrayList;
import java.util.List;

public class Bot {
    private int id;
    private List<Integer> microchips = new ArrayList<>();
    private int giveLowTo;
    private int giveHighTo;

    public Bot(int id, int giveLowTo, int giveHighTo) {
        this.id = id;
        this.giveLowTo = giveLowTo;
        this.giveHighTo = giveHighTo;
    }

    public void receive(int microchip) {
        if (hasBoth()) {
            throw new IllegalStateException(String.format("Bot (id=%d) has already two chips ...", id));
        }
        microchips.add(microchip);
    }

    public boolean isIdEqualTo(int id) {
        return this.id == id;
    }

    public boolean hasBoth() {
        return microchips.size() == 2;
    }

    public int getLow() {
        return Math.min(microchips.get(0), microchips.get(1));
    }

    public int getHigh() {
        return Math.max(microchips.get(0), microchips.get(1));
    }

    public boolean containsBoth(List<Integer> numbersToCompare) {
        return microchips.containsAll(numbersToCompare);
    }

    public void erase() {
        microchips.clear();
    }

    public int getGiveLowTo() {
        return giveLowTo;
    }

    public int getGiveHighTo() {
        return giveHighTo;
    }

    public int getId() {
        return id;
    }
}
