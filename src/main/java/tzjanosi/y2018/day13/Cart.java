package tzjanosi.y2018.day13;

import java.util.Objects;

public class Cart implements Comparable<Cart> {
    private Coordinate position;
    private Coordinate direction;
    private Turn turn = Turn.LEFT;

    public Cart(Coordinate position, Coordinate direction) {
        this.position = position;
        this.direction = direction;
    }

    public void changeDirection(char sign) {
        if (sign == '+') {
            cross();
        } else {
            if ((sign == '/' && direction.isVertical()) || (sign == '\\' && direction.isHorizontal())) {
                rotateClockWise();
            } else {
                rotateCounterClockWise();
            }
        }
    }

    public Coordinate move() {
        position = position.add(direction);
        return position;
    }

    private void rotateClockWise() {
        direction = direction.rotateClockWise();
    }

    private void rotateCounterClockWise() {
        direction = direction.rotateCounterClockWise();
    }

    private void cross() {
        direction = turn.action().apply(direction);
        turn = turn.next();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cart cart = (Cart) o;
        return Objects.equals(position, cart.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public int compareTo(Cart other) {
        return position.compareTo(other.position);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "position=" + position +
                ", direction=" + direction +
                '}';
    }

    public Coordinate getPosition() {
        return position;
    }
}
