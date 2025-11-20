package tzjanosi.y2017.day20;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Swarm {
    private Set<Particle> particles = new HashSet<>();

    public Swarm(List<String> input) {
        processInput(input);
    }

    public int start() {
        int maxStep = findMaxStep();
        for (int i = 0; (i < maxStep && particles.size() > 1); i++) {
            step();
        }
        return particles.size();
    }

    public void step() {
        Map<Vector, Set<Particle>> distribution = createDistribution();
        removeMatch(distribution);
        moveParticles();
    }

    private void moveParticles() {
        particles.forEach(Particle::step);
    }

    private int findMaxStep() {
        return particles.stream()
                .map(Particle::maxDistant)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No particles found!"));
    }

    private Map<Vector, Set<Particle>> createDistribution() {
        Map<Vector, Set<Particle>> distribution = new ConcurrentHashMap<>();
        for (Particle particle : particles) {
            Vector key = particle.getPosition();
            if (distribution.containsKey(key)) {
                distribution.get(key).add(particle);
            } else {
                distribution.put(key, new HashSet<>(Set.of(particle)));
            }
        }
        return distribution;
    }

    private void removeMatch(Map<Vector, Set<Particle>> distribution) {
        List<Particle> particlessToRemove = distribution.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .toList();
        particles.removeAll(particlessToRemove);
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
