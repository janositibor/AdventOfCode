package tzjanosi.y2019.day11;

public enum Direction {
    UP(new Coordinate(0, -1)) {
        @Override
        public Direction rotate(boolean clockwise) {
            return clockwise ? RIGHT : LEFT;
        }
    },
    RIGHT(new Coordinate(1, 0)) {
        @Override
        public Direction rotate(boolean clockwise) {
            return clockwise ? DOWN : UP;
        }
    },
    DOWN(new Coordinate(0, 1)) {
        @Override
        public Direction rotate(boolean clockwise) {
            return clockwise ? LEFT : RIGHT;
        }
    },
    LEFT(new Coordinate(-1, 0)) {
        @Override
        public Direction rotate(boolean clockwise) {
            return clockwise ? UP : DOWN;
        }
    };
    private Coordinate to;

    Direction(Coordinate to) {
        this.to = to;
    }

    public abstract Direction rotate(boolean clockwise);

    public Coordinate getTo() {
        return to;
    }
}
