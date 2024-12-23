package TZJanosi.y2024.day14;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void initTest() {
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(11,7));
        List<Robot> robots=service.getRobots();
        assertEquals(12,robots.size());
        assertEquals(new Coordinate(0,4),robots.get(0).getPosition());
        assertEquals(new Coordinate(3,-3),robots.get(0).getVelocity());
        assertEquals(new Coordinate(9,5),robots.get(robots.size()-1).getPosition());
        assertEquals(new Coordinate(-3,-3),robots.get(robots.size()-1).getVelocity());
    }
    @Test
    void calculationTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(11,7));
        service.move(100);
        service.findNumberOfRobotsInQuadrants();
        assertEquals(12,service.getSafetyFactor());
    }
    @Test
    void calculationWithProblemDataTest(){
        ReadData readData=new ReadData("input.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(101,103));
        service.move(100);
        service.findNumberOfRobotsInQuadrants();
        assertEquals(218433348,service.getSafetyFactor());
    }

}