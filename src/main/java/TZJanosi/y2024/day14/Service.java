package TZJanosi.y2024.day14;

import TZJanosi.y2024.day13.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    private List<Robot> robots=new ArrayList<>();
    private Map<Integer,Long> numberOfRobotsInQuadrants;
    private Coordinate limit;

    public Service(List<String> input,Coordinate limit){
        this.limit=limit;
        for (int i = 0; i < input.size(); i++) {
            Robot robot=RobotFromInputLine(input.get(i));
            robots.add(robot);
        }
    }
    public Long getSafetyFactor(){
        return numberOfRobotsInQuadrants.entrySet().stream().filter(e->e.getKey()>0).mapToLong(e-> e.getValue()).reduce(1,(i,j)->i*j);
    }
    public void findNumberOfRobotsInQuadrants(){
        numberOfRobotsInQuadrants=robots.stream().collect(Collectors.groupingBy(r->r.getQuadrant(limit),Collectors.counting()));
    }
    public void move(int time){
        robots.stream().forEach(r->r.positionAfterTime(time));
    }

    private Robot RobotFromInputLine(String input) {
        int xPosition;
        int yPosition;
        int xVelocity;
        int yVelocity;

        int positionOfFirstComa=input.indexOf(",");
        int positionOfFirstSpace=input.indexOf(" ");
        int positionOfLastComa=input.lastIndexOf(",");

        xPosition=Integer.parseInt(input.substring(2,positionOfFirstComa));
        yPosition=Integer.parseInt(input.substring(positionOfFirstComa+1,positionOfFirstSpace));
        xVelocity=Integer.parseInt(input.substring(positionOfFirstSpace+3,positionOfLastComa));
        yVelocity=Integer.parseInt(input.substring(positionOfLastComa+1));

        return new Robot(new Coordinate(xPosition,yPosition),new Coordinate(xVelocity,yVelocity));
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public Map<Integer, Long> getNumberOfRobotsInQuadrants() {
        return numberOfRobotsInQuadrants;
    }
}
