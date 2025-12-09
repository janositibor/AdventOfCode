package tzjanosi.y2025.day09;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Coordinate corner1;
    private Coordinate corner2;
    private List<Coordinate> corners = new ArrayList<>();
    private List<Segment> sides = new ArrayList<>();

    public Rectangle(Coordinate corner1, Coordinate corner2) {
        this.corner1 = corner1;
        this.corner2 = corner2;
        buildCorners();
        buildSides();
    }

    public boolean isInside(List<Segment> borders) {
        return sides.stream().noneMatch(s -> s.crossFromSegment(borders));
    }

    private void buildSides() {
        Segment side;
        for (int i = 0; i < corners.size(); i++) {
            int next;
            if (i == corners.size() - 1) {
                next = 0;
            } else {
                next = i + 1;
            }
            side = new Segment(corners.get(i), corners.get(next));
            boolean plusInside = false;
            boolean minusInside = false;
            if (corners.size() == 4) {
                plusInside = i > 1;
                minusInside = !plusInside;
            }
            side.setPlusInside(plusInside);
            side.setMinusInside(minusInside);
            sides.add(side);
        }
    }

    private void buildCorners() {
        Coordinate leftBottom = new Coordinate(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Coordinate rightBottom = new Coordinate(Math.max(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        Coordinate leftTop = new Coordinate(Math.min(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        Coordinate rightTop = new Coordinate(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
        if (leftTop.equals(rightTop) || rightTop.equals(rightBottom) || rightBottom.equals(leftBottom) || leftBottom.equals(leftTop)) {
            addToCorners(leftTop);
            addToCorners(rightTop);
            addToCorners(rightBottom);
            addToCorners(leftBottom);
        } else {
            corners = List.of(leftTop, rightTop, rightBottom, leftBottom);
        }
    }

    private void addToCorners(Coordinate corner) {
        if (!corners.contains(corner)) {
            corners.add(corner);
        }
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "corner1=" + corner1 +
                ", corner2=" + corner2 +
                '}';
    }
}
