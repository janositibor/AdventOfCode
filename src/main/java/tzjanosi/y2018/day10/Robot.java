package tzjanosi.y2018.day10;

public class Robot {

    private Coordinate originalPosition;
    private Coordinate position;
    private Coordinate velocity;

    public Robot(Coordinate originalPosition, Coordinate velocity) {
        this.originalPosition = originalPosition;
        this.velocity = velocity;
        position = originalPosition;
    }

    public void positionAfterTime(int time) {
        position = originalPosition.add(velocity.multiply(time));
    }

    public void move(int time) {
        position = position.add(velocity.multiply(time));
    }

    @Override
    public String toString() {
        return "Robot{" +
                "position=" + originalPosition +
                ", velocity=" + velocity +
                '}';
    }

    public Coordinate getPosition() {
        return position;
    }

    public Coordinate getOriginalPosition() {
        return originalPosition;
    }

    public Coordinate getVelocity() {
        return velocity;
    }
}
