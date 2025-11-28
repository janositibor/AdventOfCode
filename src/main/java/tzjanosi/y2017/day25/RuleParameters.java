package tzjanosi.y2017.day25;

public class RuleParameters {
    private int valueFrom0;
    private int stepDirectionFrom0;
    private char nextStateFrom0;
    private int valueFrom1;
    private int stepDirectionFrom1;
    private char nextStateFrom1;

    public RuleParameters(int valueFrom0, int stepDirectionFrom0, char nextStateFrom0, int valueFrom1, int stepDirectionFrom1, char nextStateFrom1) {
        this.valueFrom0 = valueFrom0;
        this.stepDirectionFrom0 = stepDirectionFrom0;
        this.nextStateFrom0 = nextStateFrom0;
        this.valueFrom1 = valueFrom1;
        this.stepDirectionFrom1 = stepDirectionFrom1;
        this.nextStateFrom1 = nextStateFrom1;
    }

    public int getValueFrom0() {
        return valueFrom0;
    }

    public int getStepDirectionFrom0() {
        return stepDirectionFrom0;
    }

    public char getNextStateFrom0() {
        return nextStateFrom0;
    }

    public int getValueFrom1() {
        return valueFrom1;
    }

    public int getStepDirectionFrom1() {
        return stepDirectionFrom1;
    }

    public char getNextStateFrom1() {
        return nextStateFrom1;
    }

    @Override
    public String toString() {
        return "RuleParameters{" +
                "valueFrom0=" + valueFrom0 +
                ", stepDirectionFrom0=" + stepDirectionFrom0 +
                ", nextStateFrom0=" + nextStateFrom0 +
                ", valueFrom1=" + valueFrom1 +
                ", stepDirectionFrom1=" + stepDirectionFrom1 +
                ", nextStateFrom1=" + nextStateFrom1 +
                '}';
    }
}
