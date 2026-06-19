package tzjanosi.y2018.day24;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class War {
    private List<Army> originalArmies = new ArrayList<>();
    private List<Army> armies = new ArrayList<>();

    public War(List<String> input) {
        processInput(input);
        armies = createCopyOfOriginalArmies();
    }

    public int findMinimalBoost() {
        int boostValue = 0;

        while (nationUnitsNumber("Infection") > 0) {
            reset();
            boost(boostValue);
            battle();
            boostValue++;
        }
        return winnerUnitsNumber();
    }

    private void boost(int value) {
        armies.stream().filter(a -> "Immune System".equals(a.getNation())).forEach(a -> a.boost(value));
    }

    private void reset() {
        armies = createCopyOfOriginalArmies();
    }

    private List<Army> createCopyOfOriginalArmies() {
        return originalArmies.stream().map(a -> new Army(a)).collect(Collectors.toList());
    }

    public int battle() {
        int immuneNumbers = nationUnitsNumber("Immune System");
        int infectionNumbers = nationUnitsNumber("Infection");
        int actualImmuneNumbers = Integer.MAX_VALUE;
        int actualInfectionNumbers = Integer.MAX_VALUE;

        while (thereAreArmiesForBothNation() && (immuneNumbers != actualImmuneNumbers || infectionNumbers != actualInfectionNumbers)) {
            immuneNumbers = actualImmuneNumbers;
            infectionNumbers = actualInfectionNumbers;

            Map<Army, Army> opponents = createOpponents();
            fight(opponents);
            actualImmuneNumbers = nationUnitsNumber("Immune System");
            actualInfectionNumbers = nationUnitsNumber("Infection");
        }
        return winnerUnitsNumber();
    }

    private void fight(Map<Army, Army> opponents) {
        Queue<Army> armiesOrderedByInitiative = createArmiesOrderedByInitiative(opponents.keySet());
        while (!armiesOrderedByInitiative.isEmpty()) {
            Army attacker = armiesOrderedByInitiative.poll();
            if (attacker.getEffectivePower() > 0) {
                Army defender = opponents.get(attacker);
                if (defender.attackedBy(attacker) <= 0) {
                    armies.remove(defender);
                }
            }
        }
    }

    private Map<Army, Army> createOpponents() {
        Set<Army> alreadySelected = new HashSet<>();
        Queue<Army> effectivePowerAndInitiative = createArmiesOrderedByEffectivePowerAndInitiative();
        Map<Army, Army> opponents = new ConcurrentHashMap<>();
        while (!effectivePowerAndInitiative.isEmpty()) {
            Army attacker = effectivePowerAndInitiative.poll();
            Optional<Army> optionalDefender = findOpponentTo(attacker, alreadySelected);
            if (optionalDefender.isPresent()) {
                opponents.put(attacker, optionalDefender.get());
                alreadySelected.add(optionalDefender.get());
            }
        }
        return opponents;
    }

    private Queue<Army> createArmiesOrderedByEffectivePowerAndInitiative() {
        Queue<Army> output = new PriorityQueue<>(Comparator.comparingInt(Army::getEffectivePower).reversed()
                .thenComparing(Comparator.comparingInt(Army::getInitiative).reversed()));
        for (Army army : armies) {
            output.add(army);
        }
        return output;
    }

    private Queue<Army> createArmiesOrderedByInitiative(Set<Army> inputArmies) {
        Queue<Army> output = new PriorityQueue<>(Comparator.comparingInt(Army::getInitiative).reversed());
        for (Army army : inputArmies) {
            output.add(army);
        }
        return output;
    }

    private Optional<Army> findOpponentTo(Army attacker, Set<Army> alreadySelected) {
        String attackerNation = attacker.getNation();
        return armies.stream()
                .filter(a -> !alreadySelected.contains(a))
                .filter(a -> !a.getNation().equals(attackerNation))
                .filter(a -> a.damageBy(attacker) > 0)
                .sorted(Comparator.comparingInt((Army a) -> a.damageBy(attacker)).reversed()
                        .thenComparing(Comparator.comparingInt(Army::getEffectivePower).reversed())
                        .thenComparing(Comparator.comparingInt(Army::getInitiative).reversed()))
                .findFirst();
    }

    private int winnerUnitsNumber() {
        return armies.stream().mapToInt(Army::getUnitNumber).sum();
    }

    private int nationUnitsNumber(String nation) {
        return armies.stream().filter(a -> a.getNation().equals(nation)).mapToInt(Army::getUnitNumber).sum();
    }

    private boolean thereAreArmiesForBothNation() {
        return existsArmiesByNation("Immune System") && existsArmiesByNation("Infection");
    }

    private boolean existsArmiesByNation(String nation) {
        return armies.stream().filter(a -> a.getNation().equals(nation)).count() > 0;
    }

    private void processInput(List<String> input) {
        String nation = "";
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (!line.isBlank()) {
                if (line.charAt(line.length() - 1) == ':') {
                    nation = line.substring(0, line.length() - 1);
                } else {
                    processLine(nation, line);
                }
            }
        }
    }

    private void processLine(String nation, String line) {
        String[] words = line.split(" ");
        int unitNumber = Integer.parseInt(words[0]);
        int unitsHitPoint = Integer.parseInt(words[4]);
        int initiative = Integer.parseInt(words[words.length - 1]);
        int damage = Integer.parseInt(words[words.length - 6]);
        String attackType = words[words.length - 5];
        Army army = new Army(nation, unitNumber, unitsHitPoint, attackType, damage, initiative);
        if (line.contains("(")) {
            int from = line.indexOf('(') + 1;
            int to = line.indexOf(')');
            String details = line.substring(from, to);
            List<String> immunities = collectTypes(details, "immune");
            army.setImmunities(immunities);
            List<String> weaknesses = collectTypes(details, "weak");
            army.setWeaknesses(weaknesses);
        }
        originalArmies.add(army);
    }

    private List<String> collectTypes(String details, String type) {
        List<String> output;
        if (details.contains(";")) {
            String[] parts = details.split(";");
            String stringToParse = parts[0].contains(type) ? parts[0] : parts[1];
            output = parse(stringToParse);
        } else {
            if (details.contains(type)) {
                output = parse(details);
            } else {
                output = Collections.emptyList();
            }
        }
        return output;
    }

    private List<String> parse(String stringToParse) {
        int landmarkPosition = stringToParse.indexOf("to") + 3;
        String relevantInformation = stringToParse.substring(landmarkPosition);
        String[] types = relevantInformation.split(", ");
        return Arrays.asList(types);
    }
}
