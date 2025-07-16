package tzjanosi_temp.y2016.day01;

public class Instruction {
    private String rotationDirection;
    private int stepLength;

    public Instruction(String rotationDirection, int stepLength) {
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
