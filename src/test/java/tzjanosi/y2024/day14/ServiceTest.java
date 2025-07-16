package tzjanosi.y2024.day14;

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
    @Test
    void drawTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(11,7));
        service.draw(0);

//        service.setMaxArea();
//        System.out.println(service.getMaxArea());
//        assertEquals(2, service.getMaxArea());
    }
    @Test
    void maxAreaTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(11,7));
        service.draw(0);
        service.setMaxArea();
        assertEquals(2, service.getMaxArea());
    }
    @Test
    void maxArea2Test(){
        ReadData readData=new ReadData("testInput2.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(11,7));
        service.draw(0);
        service.setMaxArea();
        assertEquals(8, service.getMaxArea());
    }
    @Test
    void cloudBasicTest(){
        ReadData readData=new ReadData("testInput2.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(11,7));
        int cloud=service.getCloud(8);
        System.out.println(service.getMaxArea());
        service.draw(cloud);
    }
    @Test
    void cloudTest(){
        ReadData readData=new ReadData("input.txt");
        Service service=new Service(readData.getOutput(),new Coordinate(101,103));
        int cloud=service.getCloud(20);
        System.out.println(service.getMaxArea());
        service.draw(cloud);
        assertEquals(6512,cloud);
    }


}