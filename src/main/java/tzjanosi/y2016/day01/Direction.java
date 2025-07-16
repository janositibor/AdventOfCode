package tzjanosi.y2016.day01;

public enum Direction {
    NORTH(new Coordinate(0, 1)) {
        @Override
        public Direction turnClockWise() {
            return EAST;
        }

        @Override
        public Direction turnCounterClockWise() {
            return WEST;
        }
    },
    SOUTH(new Coordinate(0, -1)) {
        @Override
        public Direction turnClockWise() {
            return WEST;
        }

        @Override
        public Direction turnCounterClockWise() {
            return EAST;
        }
    },
    EAST(new Coordinate(1, 0)) {
        @Override
        public Direction turnClockWise() {
            return SOUTH;
        }

        @Override
        public Direction turnCounterClockWise() {
            return NORTH;
        }
    },
    WEST(new Coordinate(-1, 0)) {
        @Override
        public Direction turnClockWise() {
            return NORTH;
        }

        @Override
        public Direction turnCounterClockWise() {
            return SOUTH;
        }
    };
    private Coordinate direction;

    Direction(Coordinate direction) {
        this.direction = direction;
    }

    public abstract Direction turnClockWise();

    public abstract Direction turnCounterClockWise();

    public Coordinate getDirection() {
        return direction;
    }
}
