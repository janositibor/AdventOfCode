package tzjanosi.y2018.day23;

import java.util.Objects;

public class FourDCoordinate {
    private long u;
    private long v;
    private long w;
    private long t;

    public FourDCoordinate(long u, long v, long w, long t) {
        this.u = u;
        this.v = v;
        this.w = w;
        this.t = t;
    }

    public FourDCoordinate(Coordinate threeDCoordinate) {
        u = threeDCoordinate.getX() + threeDCoordinate.getY() + threeDCoordinate.getZ();
        v = threeDCoordinate.getX() + threeDCoordinate.getY() - threeDCoordinate.getZ();
        w = threeDCoordinate.getX() - threeDCoordinate.getY() + threeDCoordinate.getZ();
        t = -threeDCoordinate.getX() + threeDCoordinate.getY() + threeDCoordinate.getZ();
    }

    public Coordinate transformToCoordinate() {
        long x = (v + w) / 2;
        long y = (v + t) / 2;
        long z = (w + t) / 2;
        return new Coordinate(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FourDCoordinate that = (FourDCoordinate) o;
        return u == that.u && v == that.v && w == that.w && t == that.t;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w, t);
    }

    public long getU() {
        return u;
    }

    public void setU(long u) {
        this.u = u;
    }

    public long getV() {
        return v;
    }

    public void setV(long v) {
        this.v = v;
    }

    public long getW() {
        return w;
    }

    public void setW(long w) {
        this.w = w;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }
}
