package tzjanosi.y2025.day11;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Reactor {
    private Set<Rack> racks = new HashSet<>();

    public Reactor(List<String> input) {
        processInput(input);
    }

    public long findDistanceToOutPart2() {
        Rack from = findRackByName("svr").get();
        boolean upgraded;
        do {
            upgraded = false;
            for (Rack rack : racks) {
                if (rack.getDistanceToOut() == 0) {
                    boolean success = rack.calculateDistanceToOut();
                    upgraded = upgraded || success;
                }
            }
        }
        while (from.getDistanceToOut() == 0 && upgraded);
        return from.getDistanceToOutWithBoth();
    }

    public long findDistanceToOut() {
        Rack you = findRackByName("you").get();
        boolean upgraded;
        do {
            upgraded = false;
            for (Rack rack : racks) {
                if (rack.getDistanceToOut() == 0) {
                    boolean success = rack.calculateDistanceToOut();
                    upgraded = upgraded || success;
                }
            }
        }
        while (you.getDistanceToOut() == 0 && upgraded);
        return you.getDistanceToOut();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(": ");
        Rack rack = createOrFindRackByName(parts[0]);
        addConnections(rack, parts[1]);
    }

    private void addConnections(Rack rack, String connectionsAsString) {
        String[] names = connectionsAsString.split(" ");
        for (int i = 0; i < names.length; i++) {
            Rack rackToAdd = createOrFindRackByName(names[i]);
            rack.addConnection(rackToAdd);
        }
    }

    private Rack createOrFindRackByName(String name) {
        Optional<Rack> optionalRack = findRackByName(name);
        if (optionalRack.isPresent()) {
            return optionalRack.get();
        } else {
            Rack output = new Rack(name);
            if (!"out".equals(name)) {
//                System.out.println("OUT itt?");
                racks.add(output);
            }


            return output;
        }
    }

    private Optional<Rack> findRackByName(String name) {
        return racks.stream().filter(r -> name.equals(r.getName())).findFirst();
    }
}
