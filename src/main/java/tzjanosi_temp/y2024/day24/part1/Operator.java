package tzjanosi_temp.y2024.day24.part1;

public enum Operator {
    AND {
        @Override
        public boolean calculate(boolean o1, boolean o2) {
            return o1 && o2;
        }
    },
    OR {
        @Override
        public boolean calculate(boolean o1, boolean o2) {
            return o1 || o2;
        }
    },
    XOR {
        @Override
        public boolean calculate(boolean o1, boolean o2) {
            return o1 ^ o2;
        }
    };

    public abstract boolean calculate(boolean o1, boolean o2);
}
