package TZJanosi.y2016.day01;

public class Taxi {
    private Coordinate location = new Coordinate(0, 0);
    private Direction direction = Direction.NORTH;

    public void move(Instruction instruction) {
        rotate(instruction.getRotationDirection());
        step(instruction.getStepLength());
    }

    private void rotate(String rotationDirection) {
        switch (rotationDirection) {
            case "R":
                direction = direction.turnClockWise();
                break;
            case "L":
                direction = direction.turnCounterClockWise();
                break;
            default:
                throw new IllegalArgumentException("Unknown direction to turn: " + rotationDirection);
        }
    }

    private void step(int stepLength) {
        location = location.add(direction.getDirection().multiply(stepLength));
    }

    public int calculateDistance() {
        return Math.abs(location.getX()) + Math.abs(location.getY());
    }

    public Coordinate getLocation() {
        return location;
    }
}
