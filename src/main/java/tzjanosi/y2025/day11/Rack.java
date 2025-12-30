package tzjanosi.y2025.day11;

import java.util.HashSet;
import java.util.Set;

public class Rack {
    private String name;
    private Set<Rack> connections = new HashSet<>();
    private int distanceToOut;

    public Rack(String name) {
        this.name = name;
    }

    public void addConnection(Rack rackToAdd) {
        connections.add(rackToAdd);
        if ("out".equals(rackToAdd.name)) {
            distanceToOut = 1;
        }
    }

    public boolean calculateDistanceToOut() {
        boolean output = allConnectionHasKnownDistance();
        if (output) {
            distanceToOut = connections.stream().mapToInt(Rack::getDistanceToOut).sum();
        }
        return output;
    }

    public boolean allConnectionHasKnownDistance() {
        return connections.stream().allMatch(r -> r.getDistanceToOut() > 0);
    }

    public String getName() {
        return name;
    }

    public int getDistanceToOut() {
        return distanceToOut;
    }

    @Override
    public String toString() {
        return "Rack{" +
                "name='" + name + '\'' +
                ", connections=" + connections +
                ", distanceToOut=" + distanceToOut +
                '}';
    }
}
