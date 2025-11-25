package tzjanosi.y2017.day22.part2;

public class Virus {
    private Coordinate position;
    private Coordinate direction;
    private int infectionCount;

    public Virus(Coordinate position, Coordinate direction) {
        this.position = position;
        this.direction = direction;
    }

    public void burst(Node node) {
        turn(node);
        infectOrHeal(node);
        move();
    }

    private void move() {
        position = position.move(direction);
    }

    private void infectOrHeal(Node node) {
        State nextState = node.getState().next();
        node.setState(nextState);
        if (nextState == State.INFECTED) {
            infectionCount++;
        }
    }

    private void turn(Node node) {
        direction = node.getState().changeDirection(direction);
    }

    public int getInfectionCount() {
        return infectionCount;
    }

    public Coordinate getPosition() {
        return position;
    }
}
