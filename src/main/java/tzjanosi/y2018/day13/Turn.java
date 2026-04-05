package tzjanosi.y2018.day13;

import java.util.function.UnaryOperator;

public enum Turn {
    LEFT {
        @Override
        public UnaryOperator<Coordinate> action() {
            return Coordinate::rotateCounterClockWise;
        }

        @Override
        public Turn next() {
            return STRAIGHT;
        }
    }, STRAIGHT {
        @Override
        public UnaryOperator<Coordinate> action() {
            return Coordinate::straight;
        }

        @Override
        public Turn next() {
            return RIGHT;
        }
    }, RIGHT {
        @Override
        public UnaryOperator<Coordinate> action() {
            return Coordinate::rotateClockWise;
        }

        @Override
        public Turn next() {
            return LEFT;
        }
    };

    public abstract UnaryOperator<Coordinate> action();

    public abstract Turn next();
}
