package tzjanosi.y2018.day25;

public class Coordinate {
    private int x;
    private int y;
    private int z;
    private int w;

    public Coordinate(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public boolean isCloseEnough(Coordinate other) {
        int tempX = Math.abs(x - other.x);
        int tempY = Math.abs(y - other.y);
        int tempZ = Math.abs(z - other.z);
        int tempW = Math.abs(w - other.w);
        if (tempX > 3 || tempY > 3 || tempZ > 3 || tempW > 3) {
            return false;
        }
        return tempX + tempY + tempZ + tempW <= 3;
    }
}
