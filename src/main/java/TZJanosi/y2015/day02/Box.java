package TZJanosi.y2015.day02;

public class Box {
    private int a;
    private int b;
    private int c;

    public Box(int a, int b, int c) {
        if (c < a || c < b) {
            throw new IllegalArgumentException(String.format("The third argument should be the longest: %d, %d, %d", a, b, c));
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calculateSurfaceForWrapper() {
        return calculateArea() + calculateExtraSurface();
    }

    private int calculateExtraSurface() {
        return a * b;
    }

    private int calculateArea() {
        return 2 * (a * b + a * c + b * c);
    }

}
