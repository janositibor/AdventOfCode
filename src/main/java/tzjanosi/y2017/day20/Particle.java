package tzjanosi.y2017.day20;

public class Particle {
    private int id;
    private Vector position;
    private Vector velocity;
    private Vector acceleration;

    public Particle(int id, Vector position, Vector velocity, Vector acceleration) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void step() {
        velocity = velocity.plus(acceleration);
        position = position.plus(velocity);
    }

    public int maxDistant() {
        return position.maxDistant();
    }

    public int longTermManhattanDistanceForAcceleration() {
        return acceleration.manhattanLength();
    }

    public int longTermManhattanDistanceForVelocity() {
        return velocity.manhattanLength();
    }

    public int getId() {
        return id;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "id=" + id +
                ", position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                '}';
    }
}
