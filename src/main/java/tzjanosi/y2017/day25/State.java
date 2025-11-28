package tzjanosi.y2017.day25;

public class State {
    private char name;
    private RuleParameters ruleParameters;

    public State(char name, RuleParameters ruleParameters) {
        this.name = name;
        this.ruleParameters = ruleParameters;
    }

    public int nextValue(int actualValue) {
        return actualValue == 0 ? ruleParameters.getValueFrom0() : ruleParameters.getValueFrom1();
    }

    public int nextStepDirection(int actualValue) {
        return actualValue == 0 ? ruleParameters.getStepDirectionFrom0() : ruleParameters.getStepDirectionFrom1();
    }

    public char nextState(int actualValue) {
        return actualValue == 0 ? ruleParameters.getNextStateFrom0() : ruleParameters.getNextStateFrom1();
    }

    @Override
    public String toString() {
        return "State{" +
                "name=" + name +
                ", ruleParameters=" + ruleParameters +
                '}';
    }

    public char getName() {
        return name;
    }
}
