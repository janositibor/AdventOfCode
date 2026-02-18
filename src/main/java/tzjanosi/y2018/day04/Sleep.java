package tzjanosi.y2018.day04;

import java.util.ArrayList;
import java.util.List;

public class Sleep {
    private int from;
    private int to;

    public Sleep(int from) {
        this.from = from;
    }

    public int duration() {
        return to - from;
    }

    public List<Integer> sleepingMinutes() {
        List<Integer> output = new ArrayList<>();
        for (int i = from; i < to; i++) {
            output.add(i);
        }
        return output;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Sleep{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
