package tzjanosi.y2025.day09;

import java.util.List;

public class Segment {
    private Coordinate end1;
    private Coordinate end2;
    private boolean horizontal;
    private boolean plusInside;
    private boolean minusInside;

    public Segment(Coordinate end1, Coordinate end2) {
        this.end1 = end1;
        this.end2 = end2;
        horizontal = (end1.getY() == end2.getY());
    }

    public boolean crossFromSegment(List<Segment> segments) {
        return segments.stream().anyMatch(this::crossing);
    }

    public boolean crossing(Segment other) {
        if (horizontal == other.horizontal) {
            return false;
        }
        if (horizontal) {
            return horizontalCross(other);
        } else {
            return verticalCross(other);
        }
    }

    private boolean verticalCross(Segment other) {
        boolean yBetween = minY() < other.minY() && other.maxY() < maxY();
        boolean xCross = other.minX() < minX() && maxX() < other.maxX();
        boolean touch;

        if (plusInside) {
            touch = other.minX() == minX();
        } else if (minusInside) {
            touch = other.maxX() == maxX();
        } else {
            touch = false;
        }
        return yBetween && (xCross || touch);
    }

    private boolean horizontalCross(Segment other) {
        boolean xBetween = minX() < other.minX() && other.maxX() < maxX();
        boolean yCross = other.minY() < minY() && maxY() < other.maxY();
        boolean touch;

        if (plusInside) {
            touch = other.minY() == minY();
        } else if (minusInside) {
            touch = other.maxY() == maxY();
        } else {
            touch = false;
        }
        return xBetween && (yCross || touch);
    }

    private int minX() {
        return Math.min(end1.getX(), end2.getX());
    }

    private int maxX() {
        return Math.max(end1.getX(), end2.getX());
    }

    private int minY() {
        return Math.min(end1.getY(), end2.getY());
    }

    private int maxY() {
        return Math.max(end1.getY(), end2.getY());
    }

    @Override
    public String toString() {
        return "Segment{" +
                "end1=" + end1 +
                ", end2=" + end2 +
                ", horizontal=" + horizontal +
                ", plusInside=" + plusInside +
                ", minusInside=" + minusInside +
                '}';
    }

    public void setPlusInside(boolean plusInside) {
        this.plusInside = plusInside;
    }

    public void setMinusInside(boolean minusInside) {
        this.minusInside = minusInside;
    }
}
