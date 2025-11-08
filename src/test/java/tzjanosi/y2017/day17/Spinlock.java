package tzjanosi.y2017.day17;

import java.util.ArrayList;
import java.util.List;

public class Spinlock {
    private List<Integer> spin = new ArrayList<>();
    private int limit = 2017;

    public Spinlock() {
        spin.add(0);
    }

    public int create(int shift) {
        int actualPosition = 0;
        for (int i = 1; i <= limit; i++) {
            actualPosition = ((actualPosition + shift) % spin.size()) + 1;
            spin.add(actualPosition, i);
        }
        return spin.get((actualPosition + 1) % spin.size());
    }
}
