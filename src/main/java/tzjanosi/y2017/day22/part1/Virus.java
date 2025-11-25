package tzjanosi.y2017.day22.part1;

import java.util.Set;

public class Virus {
    private Coordinate position;
    private Coordinate direction;
    private int infectionCount;

    public Virus(Coordinate position, Coordinate direction) {
        this.position = position;
        this.direction = direction;
    }

    public void burst(Set<Coordinate> infected) {
        turn(infected);
        infectOrHeal(infected);
        move();
    }

    private void move() {
        position = position.move(direction);
    }

    private void infectOrHeal(Set<Coordinate> infected) {
        if (infected.contains(position)) {
            infected.remove(position);
        } else {
            infected.add(new Coordinate(position));
            infectionCount++;
        }
    }

    private void turn(Set<Coordinate> infected) {
        direction = (infected.contains(position) ? direction.right() : direction.left());
    }

    public int getInfectionCount() {
        return infectionCount;
    }
}
