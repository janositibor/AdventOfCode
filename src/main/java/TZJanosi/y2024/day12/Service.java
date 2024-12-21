package TZJanosi.y2024.day12;

import java.util.*;

public class Service {

    public static final char REPLACE_CHARACTER='.';
    public static final char EMPTY_CHARACTER=' ';
    private Map<Coordinate,Character> points=new HashMap<>();
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
        return regions.stream().mapToLong(r->r.getCost()).sum();
    }
    public long calculateDiscountCost(){
        return regions.stream().mapToLong(r->r.getDiscountCost()).sum();
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
//        output+= getNumberOfVerticalFences();
        
        return output;
    }

    private int getNumberOfVerticalFences() {
        int output=0;
        for (int i = 0; i <= limit.getX(); i++) {
            output+=getNumberOfEdgesInColumn(i);
        }
        return output;
    }

    private int getNumberOfEdgesInColumn(int j) {
        int output=0;
        boolean state=false;
        for (int i = 0; i < limit.getY(); i++) {
            char previous=(j==0?Service.EMPTY_CHARACTER:points.get(new Coordinate(j-1,i)));
            char actual=(j==limit.getX()?Service.EMPTY_CHARACTER:points.get(new Coordinate(j,i)));

            if(previous==Service.REPLACE_CHARACTER || actual==Service.REPLACE_CHARACTER) {
                if (!state && (actual != previous)) {
                    output++;
                    state = true;
                }
                if (state && (actual == previous)) {
                    state = false;
                }
            }
        }
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
        char oldDirection=Service.REPLACE_CHARACTER;
        for (int j = 0; j < limit.getX(); j++) {
            char previous=(i==0?Service.EMPTY_CHARACTER:points.get(new Coordinate(j,i-1)));
            char actual=(i==limit.getY()?Service.EMPTY_CHARACTER:points.get(new Coordinate(j,i)));
            if(previous!=Service.EMPTY_CHARACTER && previous!=Service.REPLACE_CHARACTER){
                previous=Service.EMPTY_CHARACTER;
            }
            if(actual!=Service.EMPTY_CHARACTER && actual!=Service.REPLACE_CHARACTER){
                actual=Service.EMPTY_CHARACTER;
            }
            if(state || (previous==Service.REPLACE_CHARACTER || actual==Service.REPLACE_CHARACTER)) {
                char direction=(actual==Service.REPLACE_CHARACTER?actual:Service.EMPTY_CHARACTER);

                if ((actual != previous) && ((!state) || (state && (direction!=oldDirection) && (previous==Service.REPLACE_CHARACTER || actual==Service.REPLACE_CHARACTER)))) {
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

    private void emptyRegion() {
        Coordinate coordinate;
        int value;
        for (int i = 0; i < limit.getY(); i++) {
            for (int j = 0; j < limit.getX(); j++) {
                coordinate=new Coordinate(j,i);
                value=points.get(coordinate);
                if(value==Service.REPLACE_CHARACTER){
                    points.put(coordinate, Service.EMPTY_CHARACTER);
                }
            }
        }
    }

    private Region createRegionFrom(Point point) {
        Region result=new Region(point.getValue());
        processChildren(point,result);
        return result;
    }

    private void processChildren(Point point, Region result) {
        int numberOfGoodNeighbours;
        numberOfGoodNeighbours=getNumberOfGoodNeighbours(point);
        result.addPoint(numberOfGoodNeighbours);
        points.put(point.getCoordinate(),Service.REPLACE_CHARACTER);
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
                if(value!=Service.EMPTY_CHARACTER){
                    return new Point(coordinate, value);
                }
            }
        }
        return null;
    }
}
