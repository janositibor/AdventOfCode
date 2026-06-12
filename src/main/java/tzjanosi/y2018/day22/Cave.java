package tzjanosi.y2018.day22;

import java.util.*;

public class Cave {
    private int depth;
    private Coordinate target;
    private int[][] erosion;
    private int[][] typeMatrix;
    private Coordinate limit;
    private int[][][] shortestWays;
    private Queue<State> statesToProcess = new PriorityQueue<>();
    private int bestRouteToTarget = Integer.MAX_VALUE;

    public Cave(List<String> input) {
        processInput(input);
        fillRegions();
    }

    public int findShortestWay() {
        State start = new State(new Coordinate(0, 0), Tool.TORCH, 0);
        statesToProcess.add(start);
        updateShortestWays(start);
        while (!statesToProcess.isEmpty()) {
            State actual = statesToProcess.remove();
            processState(actual);
        }
        return bestRouteToTarget;
    }

    public int calculateRisk() {
        int output = 0;
        for (int i = 0; i <= target.getX(); i++) {
            for (int j = 0; j <= target.getY(); j++) {
                output += typeMatrix[i][j];
            }
        }
        return output;
    }

    private void updateShortestWays(State state) {
        shortestWays[state.getTool().getValue()][state.getPosition().getX()][state.getPosition().getY()] = state.getRouteLength();
    }

    private void processState(State actualState) {
        Coordinate actualPosition = actualState.getPosition();
        if (target.equals(actualPosition)) {
            int actualLength = actualState.getRouteLength();
            if (actualLength < bestRouteToTarget) {
                bestRouteToTarget = actualLength;
                deleteTooLongRoutes();
            }
        } else {
            Tool actualTool = actualState.getTool();
            List<Coordinate> neighbours = actualPosition.findNeighbours(limit);
            for (Coordinate nextCoordinate : neighbours) {
                int type = typeMatrix[nextCoordinate.getX()][nextCoordinate.getY()];
                int nextRouteLength = actualState.getRouteLength() + 1;
                Tool nextTool = actualTool;
                if (!actualTool.isCompatible(type, target.equals(nextCoordinate))) {
                    nextTool = toolForBoth(actualState.getPosition(), nextCoordinate);
                    nextRouteLength += 7;
                }
                State nextState = new State(nextCoordinate, nextTool, nextRouteLength);
                nextState.calculateEta(target);
                updateShortestWaysAndStatesToProcess(nextState);
            }
        }
    }

    private Tool toolForBoth(Coordinate actualCoordinate, Coordinate nextCoordinate) {
        Tool output;
        if (target.equals(nextCoordinate)) {
            output = Tool.TORCH;
        } else {
            int actualType = typeMatrix[actualCoordinate.getX()][actualCoordinate.getY()];
            int nextType = typeMatrix[nextCoordinate.getX()][nextCoordinate.getY()];

            switch (actualType + nextType) {
                case 1:
                    output = Tool.CLIMBING_GEAR;
                    break;
                case 2:
                    output = Tool.TORCH;
                    break;
                case 3:
                    output = Tool.NEITHER;
                    break;
                default:
                    throw new IllegalArgumentException("Unknow transmission: " + actualType + ", " + nextType);
            }
        }
        return output;
    }

    private void deleteTooLongRoutes() {
        Iterator<State> iterator = statesToProcess.iterator();
        while (iterator.hasNext()) {
            State state = iterator.next();
            if (state.getEta() >= bestRouteToTarget) {
                iterator.remove();
            }
        }
    }

    private void updateShortestWaysAndStatesToProcess(State nextState) {
        if (nextState.getEta() < bestRouteToTarget) {
            if (getShortestWay(nextState) < Integer.MAX_VALUE) {
                if (getShortestWay(nextState) > nextState.getRouteLength()) {
                    updateShortestWays(nextState);
                    if (statesToProcess.contains(nextState)) {
                        statesToProcess.remove(nextState);
                    }
                    statesToProcess.add(nextState);
                }
            } else {
                updateShortestWays(nextState);
                statesToProcess.add(nextState);
            }
        }
    }

    private int getShortestWay(State state) {
        return shortestWays[state.getTool().getValue()][state.getPosition().getX()][state.getPosition().getY()];
    }

    private void fillRegions() {
        int xLimit = 10 * target.getX();
        int yLimit = 10 * target.getY();
        shortestWays = new int[3][xLimit + 1][yLimit + 1];
        erosion = new int[xLimit + 1][yLimit + 1];
        typeMatrix = new int[xLimit + 1][yLimit + 1];
        limit = new Coordinate(xLimit, yLimit);
        for (int i = 0; i <= xLimit; i++) {
            for (int j = 0; j <= yLimit; j++) {
                erosion[i][j] = calculateErosion(new Coordinate(i, j));
                typeMatrix[i][j] = erosion[i][j] % 3;
                shortestWays[0][i][j] = Integer.MAX_VALUE;
                shortestWays[1][i][j] = Integer.MAX_VALUE;
                shortestWays[2][i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private void processInput(List<String> input) {
        depth = Integer.parseInt(input.get(0).split(" ")[1]);
        int x = Integer.parseInt(input.get(1).split(" ")[1].split(",")[0]);
        int y = Integer.parseInt(input.get(1).split(" ")[1].split(",")[1]);
        target = new Coordinate(x, y);
    }

    private int calculateErosion(Coordinate location) {
        long geologicIndex;
        if (target.equals(location)) {
            geologicIndex = 0;
        } else if (location.getY() == 0) {
            geologicIndex = (location.getX()) * 16807;
        } else if (location.getX() == 0) {
            geologicIndex = (location.getY()) * 48271;
        } else {
            geologicIndex = erosion[location.getX()][location.getY() - 1] * erosion[location.getX() - 1][location.getY()];
            if (geologicIndex < 0) {
                System.out.println("overflow");
            }
        }
        return (int) ((geologicIndex + depth) % 20183);

    }
}
