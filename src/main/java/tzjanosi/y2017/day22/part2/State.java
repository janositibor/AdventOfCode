package tzjanosi.y2017.day22.part2;

public enum State {
    CLEAN {
        @Override
        public State next() {
            return WEAKENED;
        }

        @Override
        public Coordinate changeDirection(Coordinate c) {
            return c.left();
        }
    },
    WEAKENED {
        @Override
        public State next() {
            return INFECTED;
        }

        @Override
        public Coordinate changeDirection(Coordinate c) {
            return c;
        }
    },
    INFECTED {
        @Override
        public State next() {
            return FLAGGED;
        }

        @Override
        public Coordinate changeDirection(Coordinate c) {
            return c.right();
        }
    },
    FLAGGED {
        @Override
        public State next() {
            return CLEAN;
        }

        @Override
        public Coordinate changeDirection(Coordinate c) {
            return c.turnBack();
        }
    };

    public abstract State next();

    public abstract Coordinate changeDirection(Coordinate c);
}
