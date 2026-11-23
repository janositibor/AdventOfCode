package tzjanosi.y2019.day10;

import java.util.Objects;

public class ViewDirection implements Comparable<ViewDirection> {
    private Coordinate original;
    private Coordinate simplified;
    private double angle;

    public ViewDirection(Coordinate original) {
        this.original = original;
        angle = calculateAngle();
        simplified = simplification();
    }

    private double calculateAngle() {
        int x = original.getX();
        int y = original.getY();
        double output;
        if (x == 0) {
            output = (1 + (y / Math.abs(y))) * (Math.PI / 2);
        } else {
            int sign = x / Math.abs(x);
            output = Math.PI - sign * ((Math.PI / 2)) - Math.atan((-1.0 * y) / x);
        }
        return output;
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
                ", angle=" + angle +
                '}';
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public int compareTo(ViewDirection other) {
        if (Math.abs(original.getX()) != Math.abs(other.original.getX())) {
            return Math.abs(original.getX()) - Math.abs(other.original.getX());
        }
        return Math.abs(original.getY()) - Math.abs(other.original.getY());

    }

    public Coordinate getOriginal() {
        return original;
    }
}
