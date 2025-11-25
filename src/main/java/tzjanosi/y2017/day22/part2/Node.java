package tzjanosi.y2017.day22.part2;

public class Node {
    private State state;

    public Node(State state) {
        this.state = state;
    }

    public Node() {
        this.state = State.CLEAN;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
