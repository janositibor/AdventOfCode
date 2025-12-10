package tzjanosi.y2025.day10;

import java.util.*;

public class Machine {
    private List<Boolean> requiredState;
    private List<Wiring> rules;
    private List<Boolean> actualState;
    private int numberOfAppliedRules;
    private Set<Wiring> appliedRules = new HashSet<>();

    public Machine(List<Boolean> requiredState, List<Wiring> rules) {
        this.requiredState = requiredState;
        this.rules = rules;
        buildActualState();
    }

    public int numberOfRules() {
        return rules.size();
    }

    public boolean isValid() {
        return actualState.equals(requiredState);
    }

    public boolean applyRule(int index) {
        Wiring rule = rules.get(index);
        rules.remove(rule);
        appliedRules.add(rule);
        actualState = rule.toggle(actualState);
        numberOfAppliedRules++;
        return isValid();
    }

    private void buildActualState() {
        int size = requiredState.size();
        actualState = new ArrayList<>(Collections.nCopies(size, false));
    }

    public Machine(Machine original) {
        numberOfAppliedRules = original.numberOfAppliedRules;
        requiredState = original.requiredState;
        rules = new ArrayList<>(original.rules);
        appliedRules = new HashSet<>(original.appliedRules);
        actualState = new ArrayList<>(original.actualState);
    }

    public int getNumberOfAppliedRules() {
        return numberOfAppliedRules;
    }
}
