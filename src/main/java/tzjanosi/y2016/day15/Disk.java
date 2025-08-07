package tzjanosi.y2016.day15;

public class Disk {
    private int position;
    private int slots;
    private int startingPosition;

    public Disk(int position, int slots, int startingPosition) {
        this.position = position;
        this.slots = slots;
        this.startingPosition = startingPosition;
    }

    private int positionAfterTime(int time) {
        return (startingPosition + position + time) % slots;
    }

    public boolean isFreeAfterTime(int time) {
        return positionAfterTime(time) == 0;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "position=" + position +
                ", slots=" + slots +
                ", startingPosition=" + startingPosition +
                '}';
    }
}
