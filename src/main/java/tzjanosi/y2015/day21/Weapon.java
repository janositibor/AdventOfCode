package tzjanosi.y2015.day21;

public enum Weapon implements Item {
    DAGGER(8, 4),
    SHORTSWORD(10, 5),
    WARHAMMER(25, 6),
    LONGSWORD(40, 7),
    GREATAXE(74, 8);

    private int cost;
    private int damage;

    Weapon(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getArmor() {
        return 0;
    }
}
