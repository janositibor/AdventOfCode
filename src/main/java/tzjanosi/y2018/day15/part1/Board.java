package tzjanosi.y2018.day15.part1;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private List<Coordinate> walls = new ArrayList<>();
    private Queue<Warrior> warriors = new PriorityQueue<>();
    private Set<Coordinate> alreadyVisited = new HashSet<>();
    private Queue<List<Coordinate>> routesToCheck = new LinkedList<>();

    public Board(List<String> input) {
        processInput(input);
    }

    public int game() {
        boolean noMoreEnemy = false;
        while (!noMoreEnemy) {
            Warrior warrior = warriors.poll();
            noMoreEnemy = roundForAWarrior(warrior);
            warrior.incrementRounds();
            warriors.add(warrior);
        }
        return calculateFinalScore();
    }

    private int calculateFinalScore() {
        int numberOfRounds = numberOfRounds();
        int totalHitPoints = totalHitPoints();
        return numberOfRounds * totalHitPoints;
    }

    private int numberOfRounds() {
        return warriors.stream().mapToInt(Warrior::getNumberOfRounds).min().getAsInt();
    }

    private int totalHitPoints() {
        return warriors.stream().mapToInt(Warrior::getHitPoints).sum();
    }

    private boolean roundForAWarrior(Warrior warrior) {
        Optional<Warrior> optionalEnemy = findOptionalEnemy(warrior);
        if (!optionalEnemy.isPresent()) {
            routesToCheck = new LinkedList<>();
            alreadyVisited = new HashSet<>();
            routesToCheck.add(List.of(warrior.getLocation()));
            Set<Coordinate> freeSlotsToAttack = findFreeSlotsNextToEnemies(warrior);
            Optional<List<Coordinate>> optionalRoute = findRoute(freeSlotsToAttack);
            if (optionalRoute.isPresent()) {
                warrior.move(optionalRoute.get().get(1));
            }
        }

        optionalEnemy = findOptionalEnemy(warrior);

        if (optionalEnemy.isPresent()) {
            Warrior target = optionalEnemy.get();
            boolean manageToKill = warrior.attack(target);
            if (manageToKill) {
                warriors.remove(target);
                if (countEnemies(warrior) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set<Coordinate> findFreeSlotsNextToEnemies(Warrior warrior) {
        Species typeOfEnemy = warrior.getEnemy();
        return warriors.stream()
                .filter(w -> w.getType().equals(typeOfEnemy))
                .flatMap(w -> w.getNeighbours().stream())
                .filter(this::isFreeSlot)
                .collect(Collectors.toSet());
    }

    private int countEnemies(Warrior warrior) {
        Species typeOfEnemy = warrior.getEnemy();
        return (int) warriors.stream()
                .filter(w -> w.getType().equals(typeOfEnemy))
                .count();
    }

    private Optional<Warrior> findOptionalEnemy(Warrior warrior) {
        Species typeOfEnemy = warrior.getEnemy();
        List<Coordinate> neighbours = warrior.getNeighbours();

        return warriors.stream()
                .filter(w -> w.getType().equals(typeOfEnemy))
                .filter(w -> neighbours.contains(w.getLocation()))
                .sorted(Comparator.comparingInt(Warrior::getHitPoints).thenComparing(Warrior::getLocation))
                .findFirst();

    }

    private Optional<List<Coordinate>> findRoute(Set<Coordinate> spots) {
        List<List<Coordinate>> goodPaths = new ArrayList<>();
        int maxLength = Integer.MAX_VALUE;
        while (!routesToCheck.isEmpty()) {
            List<Coordinate> route = routesToCheck.remove();
            if (route.size() < maxLength) {
                Coordinate from = route.get(route.size() - 1);
                List<Coordinate> neighbours = from.getNeighbours();
                for (int i = 0; i < neighbours.size(); i++) {
                    Coordinate next = neighbours.get(i);
                    if (spots.contains(next)) {
                        List<Coordinate> extendedRoute = new ArrayList<>(route);
                        extendedRoute.add(next);
                        goodPaths.add(extendedRoute);
                        maxLength = extendedRoute.size();
                    }
                    if (isPossibleWay(next)) {
                        List<Coordinate> extendedRoute = new ArrayList<>(route);
                        alreadyVisited.add(next);
                        extendedRoute.add(next);
                        routesToCheck.add(extendedRoute);
                    }
                }
            }
        }
        return getResultFromGoodPaths(goodPaths);
    }

    private Optional<List<Coordinate>> getResultFromGoodPaths(List<List<Coordinate>> goodPaths) {
        if (goodPaths.isEmpty()) {
            return Optional.empty();
        } else {
            if (goodPaths.size() == 1) {
                return Optional.of(goodPaths.get(0));
            } else {
                return Optional.of(routeWithHighestValue(goodPaths));
            }
        }
    }

    private List<Coordinate> routeWithHighestValue(List<List<Coordinate>> goodPaths) {
        return goodPaths.stream().min(Comparator.comparing(List::getLast)).get();

    }

    private boolean isPossibleWay(Coordinate candidate) {
        return !walls.contains(candidate) && !alreadyVisited.contains(candidate) && noWarriorAtLocation(candidate);
    }

    private boolean isFreeSlot(Coordinate candidate) {
        return !walls.contains(candidate) && noWarriorAtLocation(candidate);
    }

    private boolean noWarriorAtLocation(Coordinate candidate) {
        return warriors.stream().noneMatch(w -> w.getLocation().equals(candidate));
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(i, line);

        }
    }

    private void processLine(int y, String line) {
        for (int i = 0; i < line.length(); i++) {
            char letter = line.charAt(i);
            processLetter(y, i, letter);
        }
    }

    private void processLetter(int y, int x, char letter) {
        Coordinate coordinate = new Coordinate(x, y);
        switch (letter) {
            case '#':
                walls.add(coordinate);
                break;
            case 'G':
                Warrior goblin = new Warrior(Species.GOBLIN, coordinate);
                warriors.add(goblin);
                break;
            case 'E':
                Warrior elf = new Warrior(Species.ELF, coordinate);
                warriors.add(elf);
                break;
            default:
                break;
        }
    }
}
