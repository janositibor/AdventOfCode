package tzjanosi.y2019.day10;

import java.util.Objects;

public class ViewDirection {
    private Coordinate original;
    private Coordinate simplified;

    public ViewDirection(Coordinate original) {
        this.original = original;
        simplified = simplification();
    }

    private Coordinate simplification() {
        Coordinate output = new Coordinate(original);
        int gcd = greatestCommonDivisor(Math.abs(original.getX()), Math.abs(original.getY()));
        if (gcd > 1) {
            output.divideBy(gcd);
        }
        return output;
    }

    private int greatestCommonDivisor(int input1, int input2) {
        int a = input1;
        int b = input2;
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ViewDirection that = (ViewDirection) o;
        return Objects.equals(simplified, that.simplified);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(simplified);
    }

    @Override
    public String toString() {
        return "ViewDirection{" +
                "original=" + original +
                ", simplified=" + simplified +
                '}';
    }
}
