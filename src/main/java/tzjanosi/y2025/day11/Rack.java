package tzjanosi.y2025.day11;

import java.util.HashSet;
import java.util.Set;

public class Rack {
    private String name;
    private Set<Rack> connections = new HashSet<>();
    private long distanceToOut;
    private long distanceToOutWithDAC;
    private long distanceToOutWithFFT;
    private long distanceToOutWithBoth;

    public Rack(String name) {
        this.name = name;
    }

    private void addThisNodeToRoute() {
        if ("dac".equals(name)) {
            withDAC();
        }
        if ("fft".equals(name)) {
            withFFT();
        }
    }

    private void withFFT() {
        if (distanceToOutWithDAC > 0) {
            distanceToOutWithBoth = distanceToOutWithDAC;
        }
        distanceToOutWithFFT = distanceToOut;
    }

    private void withDAC() {
        if (distanceToOutWithFFT > 0) {
            distanceToOutWithBoth = distanceToOutWithFFT;
        }
        distanceToOutWithDAC = distanceToOut;
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
            distanceToOut = connections.stream().mapToLong(Rack::getDistanceToOut).sum();
            distanceToOutWithDAC = connections.stream().mapToLong(Rack::getDistanceToOutWithDAC).sum();
            distanceToOutWithFFT = connections.stream().mapToLong(Rack::getDistanceToOutWithFFT).sum();
            distanceToOutWithBoth = connections.stream().mapToLong(Rack::getDistanceToOutWithBoth).sum();
            addThisNodeToRoute();
        }
        return output;
    }

    public boolean allConnectionHasKnownDistance() {
        return connections.stream().allMatch(r -> r.getDistanceToOut() > 0);
    }

    public String getName() {
        return name;
    }

    public long getDistanceToOut() {
        return distanceToOut;
    }

    public long getDistanceToOutWithDAC() {
        return distanceToOutWithDAC;
    }

    public long getDistanceToOutWithFFT() {
        return distanceToOutWithFFT;
    }

    public long getDistanceToOutWithBoth() {
        return distanceToOutWithBoth;
    }

    @Override
    public String toString() {
        return "Rack{" +
                "name='" + name + '\'' +
                ", distanceToOut=" + distanceToOut +
                ", distanceToOutWithDAC=" + distanceToOutWithDAC +
                ", distanceToOutWithFFT=" + distanceToOutWithFFT +
                '}';
    }
}
