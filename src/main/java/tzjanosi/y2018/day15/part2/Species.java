package tzjanosi.y2018.day15.part2;

public enum Species {
    ELF {
        @Override
        public Species getEnemy() {
            return GOBLIN;
        }
    }, GOBLIN {
        @Override
        public Species getEnemy() {
            return ELF;
        }
    };

    public abstract Species getEnemy();
}
