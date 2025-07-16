package tzjanosi.y2016.day01;

public class Instruction {
    private String rotationDirection;
    private int stepLength;

    public Instruction(String rotationDirection, int stepLength) {
        if (stepLength < 1) {
            throw new IllegalArgumentException(String.format("The step length must be at least 1"));
        }
        this.rotationDirection = rotationDirection;
        this.stepLength = stepLength;
    }

    public String getRotationDirection() {
        return rotationDirection;
    }

    public int getStepLength() {
        return stepLength;
    }
}
