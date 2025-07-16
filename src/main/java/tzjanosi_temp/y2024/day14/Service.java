package tzjanosi_temp.y2024.day14;

import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private List<Robot> robots=new ArrayList<>();
    private Map<Integer,Long> numberOfRobotsInQuadrants;
    private Coordinate limit;
    private String emptyLine;
    private int maxArea;
    private Set<Coordinate> alreadyCheckedCoordinates=new HashSet<>();

    public Service(List<String> input,Coordinate limit){
        this.limit=limit;
        for (int i = 0; i < input.size(); i++) {
            Robot robot = robotFromInputLine(input.get(i));
            robots.add(robot);
        }
        emptyLine=" ".repeat(limit.getX());
    }

    public int getCloud(int limit){
        int output=0;
        while(maxArea<limit){
            System.out.println("step: "+output);
            output++;
            move(1);
            setMaxArea();
        }
        return output;
    }

    public void setMaxArea(){
        alreadyCheckedCoordinates=new HashSet<>();
        for (Robot robot:robots) {
            createRegionFrom(robot.getTransformedPosition());
        }
    }

    private int createRegionFrom(Coordinate point) {
        int area = 1;
        if(alreadyCheckedCoordinates.contains(point)){
            area= 0;
        }
        else {
            alreadyCheckedCoordinates.add(point);
            Set<Coordinate> neighbours = point.getNeighbours(limit);
            for (Coordinate neighbour:neighbours) {
                if (!alreadyCheckedCoordinates.contains(neighbour) && existRobotOnThisCoordinate(neighbour)) {
                    area += createRegionFrom(neighbour);
                }
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return area;
    }

    private boolean existRobotOnThisCoordinate(Coordinate coordinate) {
        return robots.stream().anyMatch(r->r.getTransformedPosition().equals(coordinate));
    }

    private String lineToDraw(int i){
        StringBuilder line=new StringBuilder(emptyLine);
        List<Integer> occupiedPositions=robots.stream()
                .filter(r->r.getTransformedPosition().getY()==i)
                .map(r->r.getTransformedPosition().getX())
                .toList();
        for (int index:occupiedPositions) {
            line.setCharAt(index,'+');
        }
        return line.toString();
    }
    public void draw(int info){
        System.out.printf("After %d steps\n",info);
        for (int i = 0; i < limit.getY(); i++) {
            System.out.println(lineToDraw(i));

        }
    }
    public Long getSafetyFactor(){
        return numberOfRobotsInQuadrants.entrySet().stream().filter(e -> e.getKey() > 0).mapToLong(Map.Entry::getValue).reduce(1, (i, j) -> i * j);
    }
    public void findNumberOfRobotsInQuadrants(){
        numberOfRobotsInQuadrants = robots.stream().collect(Collectors.groupingBy(Robot::getQuadrant, Collectors.counting()));
    }
    public void move(int time){
        robots.stream().forEach(r->r.positionAfterTime(time));
    }

    private Robot robotFromInputLine(String input) {
        int xPosition;
        int yPosition;
        int xVelocity;
        int yVelocity;

        int positionOfFirstComa = input.indexOf(',');
        int positionOfFirstSpace = input.indexOf(' ');
        int positionOfLastComa = input.lastIndexOf(',');

        xPosition=Integer.parseInt(input.substring(2,positionOfFirstComa));
        yPosition=Integer.parseInt(input.substring(positionOfFirstComa+1,positionOfFirstSpace));
        xVelocity=Integer.parseInt(input.substring(positionOfFirstSpace+3,positionOfLastComa));
        yVelocity=Integer.parseInt(input.substring(positionOfLastComa+1));

        return new Robot(new Coordinate(xPosition,yPosition),new Coordinate(xVelocity,yVelocity),limit);
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public int getMaxArea() {
        return maxArea;
    }

    public Map<Integer, Long> getNumberOfRobotsInQuadrants() {
        return numberOfRobotsInQuadrants;
    }
}
