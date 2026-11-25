package tzjanosi.y2019.day12;

import java.util.List;

public class Moon {
    private Vector position;
    private Vector velocity;

    public Moon(List<Integer> coordinates) {
        position = new Vector(coordinates.get(0), coordinates.get(1), coordinates.get(2));
        velocity = new Vector(0, 0, 0);
    }

    public Moon(Moon original) {
        position = new Vector(original.position);
        velocity = new Vector(original.velocity);
    }

    public int energy() {
        return position.energy() * velocity.energy();
    }

    public void step(Vector force) {
        applyForce(force);
        moveOneStep();
    }

    private void applyForce(Vector force) {
        velocity = velocity.add(force);
    }

    private void moveOneStep() {
        position = position.add(velocity);
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getZ() {
        return position.getZ();
    }

    @Override
    public String toString() {
        return "Moon{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }
}
