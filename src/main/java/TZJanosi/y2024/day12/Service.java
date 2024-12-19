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

    public void calculation(){
        Point point;
        Region region;
        while((point=getNextNotEmptyPoint())!=null){
            region=createRegionFrom(point);
            region.calculateCost();
            regions.add(region);
            emptyRegion();
        }
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
