package tzjanosi.y2018.day18;

import java.util.Set;

public enum Acre {
    OPEN_GROUND('.') {
        @Override
        public boolean check(Set<Field> neighbours) {
            int numberOfNeighbourTrees = (int) neighbours.stream().filter(f -> f.getType().equals(TREES)).count();
            return numberOfNeighbourTrees >= 3;
        }

        @Override
        public Acre turn(Set<Field> neighbours) {
            if (check(neighbours)) {
                return TREES;
            } else {
                return OPEN_GROUND;
            }
        }
    },
    TREES('|') {
        @Override
        public boolean check(Set<Field> neighbours) {
            int numberOfNeighbourLumberyards = (int) neighbours.stream().filter(f -> f.getType().equals(LUMBERYARD)).count();
            return numberOfNeighbourLumberyards >= 3;
        }

        @Override
        public Acre turn(Set<Field> neighbours) {
            if (check(neighbours)) {
                return LUMBERYARD;
            } else {
                return TREES;
            }
        }
    },
    LUMBERYARD('#') {
        @Override
        public boolean check(Set<Field> neighbours) {
            int numberOfNeighbourLumberyards = (int) neighbours.stream().filter(f -> f.getType().equals(LUMBERYARD)).count();
            int numberOfNeighbourTrees = (int) neighbours.stream().filter(f -> f.getType().equals(TREES)).count();
            return numberOfNeighbourLumberyards >= 1 && numberOfNeighbourTrees >= 1;
        }

        @Override
        public Acre turn(Set<Field> neighbours) {
            if (check(neighbours)) {
                return LUMBERYARD;
            } else {
                return OPEN_GROUND;
            }
        }
    };

    private char sign;

    Acre(char sign) {
        this.sign = sign;
    }

    public abstract boolean check(Set<Field> neighbours);

    public abstract Acre turn(Set<Field> neighbours);

    public char getSign() {
        return sign;
    }
}
