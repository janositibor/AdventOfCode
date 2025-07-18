package tzjanosi.y2016.day03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Triangle {
    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) {
        List<Integer> sides = Arrays.asList(a, b, c);
        sides.sort(Comparator.naturalOrder());
        this.a = sides.get(0);
        this.b = sides.get(1);
        this.c = sides.get(2);
    }

    public boolean isValid() {
        return (a + b) > c;
    }
}
