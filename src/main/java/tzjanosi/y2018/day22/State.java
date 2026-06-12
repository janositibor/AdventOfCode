package tzjanosi.y2018.day22;

import java.util.Objects;

public class State implements Comparable<State> {
    private Coordinate position;
    private Tool tool;
    private int routeLength;
    private int eta;

    public State(Coordinate position, Tool tool, int routeLength) {
        this.position = position;
        this.tool = tool;
        this.routeLength = routeLength;
    }

    public void calculateEta(Coordinate target) {
        eta = routeLength + position.manhattanDistanceTo(target);
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    @Override
    public int compareTo(State other) {
//        return eta-other.eta;
        return routeLength - other.routeLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(position, state.position) && tool == state.tool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, tool);
    }

    public Coordinate getPosition() {
        return position;
    }

    public Tool getTool() {
        return tool;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public int getEta() {
        return eta;
    }

    @Override
    public String toString() {
        return "State{" +
                "position=" + position +
                ", tool=" + tool +
                ", routeLength=" + routeLength +
                ", eta=" + eta +
                '}';
    }
}
