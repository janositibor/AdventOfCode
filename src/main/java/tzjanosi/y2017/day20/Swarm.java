package tzjanosi.y2017.day20;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Swarm {
    private Set<Particle> particles = new HashSet<>();

    public Swarm(List<String> input) {
        processInput(input);
    }

    public int idForLongTermMinimalManhattanDistance() {
        return particles.stream()
                .sorted(Comparator.comparingInt(Particle::longTermManhattanDistanceForAcceleration).thenComparing(Particle::longTermManhattanDistanceForVelocity))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Empty particle list!"))
                .getId();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(i, input.get(i));
        }
    }

    private void processLine(int id, String line) {
        String[] words = line.split(", ");
        Vector position = buildVector(words[0]);
        Vector velocity = buildVector(words[1]);
        Vector acceleration = buildVector(words[2]);

        particles.add(new Particle(id, position, velocity, acceleration));

    }

    private Vector buildVector(String word) {
        String[] parts = word.split("[<>,]");
        return new Vector(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }
}
