package tzjanosi.y2018.day15;

import java.util.List;

public class Warrior implements Comparable<Warrior> {
    private Species type;
    private Coordinate location;
    private int hitPoints = 200;
    private int attackPower = 3;
    private List<Coordinate> neighbours;
    private int numberOfRounds;

    public Warrior(Species type, Coordinate location) {
        this.type = type;
        this.location = location;
        buildNeighbours();
    }

    public Species getEnemy() {
        return type.getEnemy();
    }

    public void incrementRounds() {
        numberOfRounds++;
    }

    private void buildNeighbours() {
        neighbours = location.getNeighbours();
    }

    public void move(Coordinate newLocation) {
        location = newLocation;
        buildNeighbours();
    }

    public boolean attack(Warrior target) {
        int health = target.hitPoints;
        health -= attackPower;
        if (health <= 0) {
            return true;
        }
        target.hitPoints = health;
        return false;
    }

    @Override
    public int compareTo(Warrior other) {
        if (numberOfRounds == other.numberOfRounds) {
            return location.compareTo(other.location);
        } else {
            return numberOfRounds - other.numberOfRounds;
        }
    }

    public Species getType() {
        return type;
    }

    public Coordinate getLocation() {
        return location;
    }

    public List<Coordinate> getNeighbours() {
        return neighbours;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "type=" + type +
                ", location=" + location +
                ", hitPoints=" + hitPoints +
                ", numberOfRounds=" + numberOfRounds +
                '}';
    }
}
