package tzjanosi.y2025.day10.part2;

import java.util.*;
import java.util.stream.Collectors;

public class Machine {
    private List<Wiring> rules;
    private int numberOfAppliedRules;
    private List<Integer> requiredVoltage;
    private Map<Integer, Set<Wiring>> groupedRules;

    public Machine(List<Wiring> rules, List<Integer> requiredVoltage) {
        this.rules = rules;
        this.requiredVoltage = requiredVoltage;
        createGroupedRules();
    }

    public Machine(Machine original) {
        numberOfAppliedRules = original.numberOfAppliedRules;
        rules = new ArrayList<>();
        for (int i = 0; i < original.rules.size(); i++) {
            rules.add(new Wiring(original.rules.get(i)));
        }
        requiredVoltage = new ArrayList<>(original.requiredVoltage);
        createGroupedRules();
    }

    public int positiveValuesInRequiredVoltage() {
        return (int) requiredVoltage.stream().filter(d -> d > 0).count();
    }

    private void createGroupedRules() {
        groupedRules = new HashMap<>();
        for (int i = 0; i < requiredVoltage.size(); i++) {
            final int numberToCheck = i;
            Set<Wiring> rulesContains = rules.stream().filter(w -> w.contains(numberToCheck)).collect(Collectors.toSet());
            groupedRules.put(i, rulesContains);
        }
    }

    private boolean linearCombination() {
        createGroupedRules();
        if (unreachableVoltage()) {
            return false;
        }
        int counter = 0;
        int updated = 1;
        while (counter < requiredVoltage.size() || updated == 1) {
            if (updated == 1) {
                counter = 0;
            }
            updated = simplifyWith(counter);
            if (updated == 1) {
                createGroupedRules();
            }
            if (updated == -1) {
                return false;
            }
            counter++;
        }
        return true;
    }

    private boolean unreachableVoltage() {
        for (int i = 0; i < requiredVoltage.size(); i++) {
            if (requiredVoltage.get(i) > 0 && groupedRules.get(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean simplify() {
        if (!linearCombination()) {
            return false;
        }
        boolean zeroRemoved;
        boolean triviaRemoved;

        do {
            triviaRemoved = removeTrivias();
            if (triviaRemoved && !linearCombination()) {
                return false;
            }
            zeroRemoved = removeZeroValues();
            if (zeroRemoved && !linearCombination()) {
                return false;
            }
        }
        while (triviaRemoved || zeroRemoved);
        return true;
    }

    private boolean removeZeroValues() {
        createGroupedRules();
        List<Integer> numbersToRemove = zeroValuesFromRequiredVoltage();
        boolean output = false;
        for (int i = 0; i < numbersToRemove.size(); i++) {
            Set<Wiring> rulesToEliminate = groupedRules.get(numbersToRemove.get(i));
            for (Wiring rule : rulesToEliminate) {
                rules.remove(rule);
                output = true;
            }
        }
        return output;
    }

    private List<Integer> zeroValuesFromRequiredVoltage() {
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < requiredVoltage.size(); i++) {
            if (requiredVoltage.get(i) == 0) {
                output.add(i);
            }
        }
        return output;
    }

    public Map<Wiring, Integer> getNextRule() {
        int maxLengthOfRules = maxLengthOfRules();
        List<Wiring> rulesWithMaxLength = rules.stream().filter(w -> w.numberOfElements() == maxLengthOfRules).toList();
        return calculateMaxRepetitionNumber(rulesWithMaxLength);
    }

    private Map<Wiring, Integer> calculateMaxRepetitionNumber(List<Wiring> rulesWithMaxLength) {
        Integer actualMax = 0;
        Wiring winner = null;
        for (int i = 0; i < rulesWithMaxLength.size(); i++) {
            Wiring rule = rulesWithMaxLength.get(i);
            int nextMax = calculateMaxRepetitionNumberForARule(rule);
            if (nextMax > actualMax) {
                actualMax = nextMax;
                winner = rule;
            }
        }
        return Map.of(winner, actualMax);
    }

    private int calculateMaxRepetitionNumberForARule(Wiring rule) {
        return rule.getPositionsToIncrease().stream().mapToInt(requiredVoltage::get).min().getAsInt();
    }

    private int maxLengthOfRules() {
        return rules.stream().mapToInt(Wiring::numberOfElements).max().orElseThrow(() -> new IllegalStateException("Empty rules set"));
    }

    private boolean removeTrivias() {
        createGroupedRules();
        boolean output = false;
        List<Wiring> rulesToEliminate = groupedRules.entrySet().stream().filter((Map.Entry<Integer, Set<Wiring>> e) -> e.getValue().size() == 1).map(e -> e.getValue().stream().findFirst().get()).toList();
        for (int i = 0; i < rulesToEliminate.size(); i++) {
            Wiring rule = rulesToEliminate.get(i);
            Set<Integer> numbersToEliminate = rule.getPositionsToIncrease();
            if (numbersToEliminate.size() > 1) {
                throw new IllegalStateException("Number Of elements in rule elimination should be one!");
            }
            int numberToEliminate = numbersToEliminate.stream().findFirst().get();
            int value = requiredVoltage.get(numberToEliminate);
            numberOfAppliedRules += value;
            requiredVoltage.set(numberToEliminate, 0);
            rules.remove(rule);
            output = true;
        }
        return output;
    }

    private int simplifyWith(int numberToSimplifyWith) {
        int output = 0;
        Set<Wiring> rulesToSimplify = groupedRules.get(numberToSimplifyWith);
        if (!rulesToSimplify.isEmpty()) {
            Wiring principalRule = rulesToSimplify.stream().findFirst().orElseThrow(() -> new IllegalStateException("No rule found"));
            if (rulesToSimplify.size() == 1) {
                int elementsInRule = rulesToSimplify.stream()
                        .findFirst()
                        .get()
                        .getPositionsToIncrease()
                        .size();
                if (elementsInRule > 1) {
                    output = simplifyOneElement(numberToSimplifyWith, rulesToSimplify);
                }
            } else {
                output = simplifyManyElement(numberToSimplifyWith, principalRule, rulesToSimplify);
            }
        }
        return output;
    }

    private int simplifyOneElement(int numberToSimplifyWith, Set<Wiring> rulesToSimplify) {
        int output = 0;
        if (rulesToSimplify.stream()
                .findFirst()
                .get()
                .getPositionsToIncrease()
                .size() > 1) {
            if (rulesToSimplify.size() > 1) {
                throw new IllegalStateException("Only one rule is expected as the parameter of simplifyOneElement!");
            }
            Set<Integer> numbersToRemove = rulesToSimplify.stream()
                    .findFirst()
                    .get()
                    .getPositionsToIncrease()
                    .stream()
                    .filter(d -> d != numberToSimplifyWith)
                    .collect(Collectors.toSet());
            for (int numberToRemove : numbersToRemove) {
                output = removeNumber(numberToRemove, requiredVoltage.get(numberToSimplifyWith), rulesToSimplify);
                if (output == -1) {
                    return -1;
                }
            }
        }
        return output;
    }

    private int simplifyManyElement(int numberToSimplifyWith, Wiring principalRule, Set<Wiring> rulesToSimplify) {
        int output = 0;
        Set<Integer> numbersToRemove = new HashSet<>(principalRule.getPositionsToIncrease());
        for (Integer numberInPrincipal : numbersToRemove) {
            if (numberInPrincipal != numberToSimplifyWith) {
                int success = removeNumber(numberInPrincipal, requiredVoltage.get(numberToSimplifyWith), rulesToSimplify);
                if (success == -1) {
                    return -1;
                }
                if (success == 1) {
                    output = success;
                }
            }
        }
        return output;
    }

    private int removeNumber(Integer numberToRemove, int valueToDecrease, Set<Wiring> rulesToSimplify) {
        if (rulesToSimplify.stream().allMatch(w -> w.contains(numberToRemove))) {
            rulesToSimplify.stream().forEach(w -> w.removeElement(numberToRemove));
            int newValue = requiredVoltage.get(numberToRemove) - valueToDecrease;
            if (newValue < 0) {
                return -1;
            }
            requiredVoltage.set(numberToRemove, newValue);
            return 1;
        }
        return 0;
    }

    public int numberOfRules() {
        return rules.size();
    }

    public int getNumberOfAppliedRules() {
        return numberOfAppliedRules;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "rules=" + rules +
                ", numberOfAppliedRules=" + numberOfAppliedRules +
                ", requiredVoltage=" + requiredVoltage +
                ", groupedRules=" + groupedRules +
                '}';
    }

    public void applyRule(Wiring ruleToApply, int count) {
        Set<Integer> elements = ruleToApply.getPositionsToIncrease();
        for (int element : elements) {
            int newValue = requiredVoltage.get(element) - count;
            requiredVoltage.set(element, newValue);
        }
        rules.remove(ruleToApply);
        numberOfAppliedRules += count;
        createGroupedRules();
    }
}