package tzjanosi.y2015.day07;

public enum Operator {
    AND {
        @Override
        public int calculate(int o1, int o2) {
            return o1 & o2;
        }
    },
    OR {
        @Override
        public int calculate(int o1, int o2) {
            return o1 | o2;
        }
    },
    LSHIFT {
        @Override
        public int calculate(int o1, int o2) {
            return o1 << o2;
        }
    },
    RSHIFT {
        @Override
        public int calculate(int o1, int o2) {
            return o1 >>> o2;
        }
    },
    NOT {
        @Override
        public int calculate(int o1, int o2) {
            return 65535 - o1;
        }
    };

    public abstract int calculate(int o1, int o2);
}
