package TZJanosi.y2015.day20;

import java.util.HashSet;
import java.util.Set;

public class House {
    private int id;
    private Set<Integer> dividers = new HashSet<>();

    public House(int id) {
        this.id = id;
        findDividers();
    }

    public int getPresentsValue() {
        return 10 * dividers.stream().mapToInt(x -> x.intValue()).sum();
    }

    private void findDividers() {
        dividers.add(1);
        if (id > 1) {
            dividers.add(id);
        }
        double sqrtId = Math.sqrt(id);
        for (int i = 2; i <= sqrtId; i++) {
            if (id % i == 0) {
                dividers.add(i);
                int pair = id / i;
                if (!dividers.contains(pair)) {
                    dividers.add(pair);
                }
            }
        }
    }

    public Set<Integer> getDividers() {
        return dividers;
    }
}
