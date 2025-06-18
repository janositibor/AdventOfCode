package TZJanosi.y2015.day21;

public enum Weapon implements Item {
    Dagger(8, 4),
    Shortsword(10, 5),
    Warhammer(25, 6),
    Longsword(40, 7),
    Greataxe(74, 8);

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
