package tzjanosi.y2018.day22;

public enum Tool {
    CLIMBING_GEAR(0) {
        @Override
        public boolean isCompatible(int type, boolean isTarget) {
            return !isTarget && type < 2;
        }
    },

    TORCH(1) {
        @Override
        public boolean isCompatible(int type, boolean isTarget) {
            return !isTarget && type % 2 == 0;
        }
    },

    NEITHER(2) {
        @Override
        public boolean isCompatible(int type, boolean isTarget) {
            return !isTarget && type > 0;
        }
    };

    private int value;

    Tool(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract boolean isCompatible(int type, boolean isTarget);
}
