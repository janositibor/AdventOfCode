package tzjanosi.y2024.day12;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Service {

    public static final char REPLACE_CHARACTER='.';
    public static final char EMPTY_CHARACTER=' ';
    private Map<Coordinate, Character> points = new ConcurrentHashMap<>();
    private Coordinate limit;
    private List<Region> regions=new ArrayList<>();

    public Service(List<String> input) {
        limit=new Coordinate(input.get(0).length(),input.size());
        setPoints(input);
    }

    private void setPoints(List<String> input) {
        for (int i = 0; i < limit.getY(); i++) {
            for (int j = 0; j < limit.getX(); j++) {
                points.put(new Coordinate(j,i),input.get(i).charAt(j));
            }
        }
    }
    public long calculateCost(){
        return regions.stream().mapToLong(Region::getCost).sum();
    }
    public long calculateDiscountCost(){
        return regions.stream().mapToLong(Region::getDiscountCost).sum();
    }

    public void calculation(){
        Point point;
        Region region;
        while((point=getNextNotEmptyPoint())!=null){
            region=createRegionFrom(point);
            region.setNumberOfFence(getNumberOfFence());
            region.calculateCost();
            regions.add(region);
            emptyRegion();
        }
    }

    private int getNumberOfFence() {
        int output=0;
        output+=(2*getNumberOfHorizontalFences());
        return output;
    }

    private int getNumberOfHorizontalFences() {
        int output=0;
        for (int i = 0; i <= limit.getY(); i++) {
            output+=getNumberOfEdgesInLine(i);
        }
        return output;
    }

    private int getNumberOfEdgesInLine(int i) {
        int output=0;
        boolean state=false;
        char oldDirection = REPLACE_CHARACTER;
        for (int j = 0; j < limit.getX(); j++) {
            char previous = buildPrevious(i, j);
            char actual = buildActual(i, j);
            previous = buildCharacter(previous);
            actual = buildCharacter(actual);

            if (condition1(state, previous, actual)) {
                char direction = (actual == REPLACE_CHARACTER ? actual : EMPTY_CHARACTER);

                if (condition2(actual, previous, state, direction, oldDirection)) {
                    output++;
                    state = true;
                    oldDirection=direction;
                }
                if (state && (actual == previous)) {
                    state = false;
                }
            }
        }
        return output;
    }

    private static boolean condition2(char actual, char previous, boolean state, char direction, char oldDirection) {
        return (actual != previous) && ((!state) || (state && (direction != oldDirection) && (previous == REPLACE_CHARACTER || actual == REPLACE_CHARACTER)));
    }

    private static boolean condition1(boolean state, char previous, char actual) {
        return state || (previous == REPLACE_CHARACTER || actual == REPLACE_CHARACTER);
    }

    private char buildActual(int i, int j) {
        return i == limit.getY() ? EMPTY_CHARACTER : points.get(new Coordinate(j, i));
    }

    private char buildPrevious(int i, int j) {
        return i == 0 ? EMPTY_CHARACTER : points.get(new Coordinate(j, i - 1));
    }

    private char buildCharacter(char inputChar) {
        char output = inputChar;
        if (inputChar != EMPTY_CHARACTER && inputChar != REPLACE_CHARACTER) {
            output = EMPTY_CHARACTER;
        }
        return output;
    }

    private void emptyRegion() {
        Coordinate coordinate;
        int value;
        for (int i = 0; i < limit.getY(); i++) {
            for (int j = 0; j < limit.getX(); j++) {
                coordinate=new Coordinate(j,i);
                value=points.get(coordinate);
                if (value == REPLACE_CHARACTER) {
                    points.put(coordinate, EMPTY_CHARACTER);
                }
            }
        }
    }

    private Region createRegionFrom(Point point) {
        Region result = new Region();
        processChildren(point,result);
        return result;
    }

    private void processChildren(Point point, Region result) {
        int numberOfGoodNeighbours;
        numberOfGoodNeighbours=getNumberOfGoodNeighbours(point);
        result.addPoint(numberOfGoodNeighbours);
        points.put(point.getCoordinate(), REPLACE_CHARACTER);
        for (Point child: getChildCoordinatesToProcess(point)) {
            if(child.getValue()==points.get(child.getCoordinate())){
                processChildren(child, result);
            }
        }
    }




    private Set<Point> getChildCoordinatesToProcess(Point point) {
        Set<Point> result=new HashSet<>();
        Coordinate coordinate=point.getCoordinate();
        Point neighbourPoint;
        Set<Coordinate> neighbours=coordinate.getNeighbours(limit);
        for (Coordinate neighbourCoordinate:neighbours) {
            neighbourPoint=new Point(neighbourCoordinate,points.get(neighbourCoordinate));
            if(point.isSameNeighbour(neighbourPoint,limit)){
                result.add(neighbourPoint);
            }
        }
        return result;
    }

    private int getNumberOfGoodNeighbours(Point point) {
        int result=0;
        Coordinate coordinate=point.getCoordinate();
        Point neighbourPoint;
        Set<Coordinate> neighbours=coordinate.getNeighbours(limit);
        for (Coordinate neighbourCoordinate:neighbours) {
            neighbourPoint=new Point(neighbourCoordinate,points.get(neighbourCoordinate));
            if(point.isGoodNeighbour(neighbourPoint,limit)){
                result++;
            }
        }
        return result;
    }

    private Point getNextNotEmptyPoint(){
        Coordinate coordinate;
        char value;
        for (int i = 0; i < limit.getY(); i++) {
            for (int j = 0; j < limit.getX(); j++) {
                coordinate=new Coordinate(j,i);
                value=points.get(coordinate);
                if (value != EMPTY_CHARACTER) {
                    return new Point(coordinate, value);
                }
            }
        }
        return null;
    }
}
