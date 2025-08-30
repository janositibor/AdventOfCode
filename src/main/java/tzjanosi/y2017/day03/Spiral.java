package tzjanosi.y2017.day03;

public class Spiral {
    public int getDistance(int order) {
        if (order == 1) {
            return 0;
        }
        int insideSquareSideLength = getInsideSquareSideLength(order);
        int remain = (int) (order - Math.pow(insideSquareSideLength, 2));
        Coordinate coordinate = calculateCoordinate(remain, insideSquareSideLength);
        return coordinate.getManhattanDistanceFromOrigo();
    }

    private Coordinate calculateCoordinate(int input, int insideSquareSideLength) {
        int remain = input;
        int start = insideSquareSideLength / 2;
        Coordinate coordinate = new Coordinate(start + 1, start);
        remain--;
        if (remain <= insideSquareSideLength) {
            return coordinate.shift(new Coordinate(0, -1 * remain));
        }
        coordinate = coordinate.shift(new Coordinate(0, -1 * (insideSquareSideLength)));
        remain -= insideSquareSideLength;

        if (remain <= insideSquareSideLength + 1) {
            return coordinate.shift(new Coordinate(-1 * remain, 0));
        }
        coordinate = coordinate.shift(new Coordinate(-1 * (insideSquareSideLength + 1), 0));
        remain -= (insideSquareSideLength + 1);

        if (remain <= insideSquareSideLength + 1) {
            return coordinate.shift(new Coordinate(0, remain));
        }
        coordinate = coordinate.shift(new Coordinate(0, 1 * (insideSquareSideLength + 1)));
        remain -= (insideSquareSideLength + 1);

        coordinate = coordinate.shift(new Coordinate(1 * remain, 0));

        return coordinate;
    }

    private int getInsideSquareSideLength(int order) {
        int index = -1;
        do {
            index += 2;
        }
        while (Math.pow(index, 2) < order);
        return index - 2;
    }
}
