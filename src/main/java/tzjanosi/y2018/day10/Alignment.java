package tzjanosi.y2018.day10;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alignment {
    private List<Robot> robots = new ArrayList<>();
    private int maxArea;
    private Set<Coordinate> alreadyCheckedCoordinates = new HashSet<>();

    public Alignment(List<String> input) {
        processInput(input);
    }

    public int getCloud(int limit) {
        int output = 0;
        while (maxArea < limit) {
            move(1);
            setMaxArea();
            output++;
            System.out.println("step: " + output + ", maxArea: " + maxArea);
            if (maxArea >= limit) {
                draw(output);
            }
        }
        return output;
    }

    private String lineToDraw(int i) {
        Coordinate xLimits = getLimits(r -> r.getPosition().getX());
        int from = xLimits.getX();
        StringBuilder line = new StringBuilder(" ".repeat(xLimits.getY() - xLimits.getX() + 1));

        List<Integer> occupiedPositions = robots.stream()
                .filter(r -> r.getPosition().getY() == i)
                .map(r -> r.getPosition().getX())
                .toList();
        for (int index : occupiedPositions) {
            line.setCharAt(index - from, '#');
        }
        return line.toString();
    }

    private List<String> draw(int info) {
        List<String> output = new ArrayList<>();
        Coordinate yLimits = getLimits(r -> r.getPosition().getY());
        System.out.printf("After %d steps\n", info);
        for (int i = yLimits.getX(); i <= yLimits.getY(); i++) {
            String line = lineToDraw(i);
            System.out.println(line);
            output.add(line);
        }
        return output;
    }

    private Coordinate getLimits(Function<Robot, Integer> extractor) {
        IntSummaryStatistics stats = robots.stream().mapToInt(extractor::apply).summaryStatistics();
        return new Coordinate(stats.getMin(), stats.getMax());
    }

    public List<String> cloudAfterMove(int steps) {
        move(steps);
        return draw(steps);
    }

    private void move(int time) {
        robots.stream().forEach(r -> r.move(time));
    }

    private void setMaxArea() {
        alreadyCheckedCoordinates = new HashSet<>();
        for (Robot robot : robots) {
            createRegionFrom(robot.getPosition());
        }
    }

    private int createRegionFrom(Coordinate point) {
        int area = 1;
        if (alreadyCheckedCoordinates.contains(point)) {
            area = 0;
        } else {
            alreadyCheckedCoordinates.add(point);
            Set<Coordinate> neighbours = point.getNeighbours();
            for (Coordinate neighbour : neighbours) {
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
        return robots.stream().anyMatch(r -> r.getPosition().equals(coordinate));
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            Robot robot = robotFromLine(input.get(i));
            robots.add(robot);
        }
    }

    private Robot robotFromLine(String line) {
        Pattern pattern = Pattern.compile(
                "position=<\\s*(-?\\d+),\\s*(-?\\d+)>\\s*velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>"
        );
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            int px = Integer.parseInt(matcher.group(1));
            int py = Integer.parseInt(matcher.group(2));
            int vx = Integer.parseInt(matcher.group(3));
            int vy = Integer.parseInt(matcher.group(4));

            return new Robot(new Coordinate(px, py), new Coordinate(vx, vy));
        } else {
            throw new IllegalArgumentException("Invalid input line format: +line");
        }
    }
}
