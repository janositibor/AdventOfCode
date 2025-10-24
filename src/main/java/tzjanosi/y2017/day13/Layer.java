package tzjanosi.y2017.day13;

public class Layer {
    private int position;
    private int depth;
    private int period;

    public Layer(int position, int depth) {
        this.position = position;
        this.depth = depth;
        period = 2 * (depth - 1);
    }

    public int severity() {
        if (positionAfterTime(position) == 0) {
            return position * depth;
        }
        return 0;
    }

    public int positionAfterTime(int time) {
        int effectiveTime = effectiveTime(time);
        return getPosition(effectiveTime);
    }

    public int getPosition(int when) {
        int actualTime = when;
        if (actualTime > period / 2) {
            return period - actualTime;
        } else {
            return actualTime;
        }
    }

    private int effectiveTime(int time) {
        if (period == 0) {
            return 0;
        }
        int effectiveTime = time;
        while (effectiveTime >= period) {
            effectiveTime -= period;
        }
        return effectiveTime;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "position=" + position +
                ", depth=" + depth +
                '}';
    }
}
