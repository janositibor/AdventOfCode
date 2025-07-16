package tzjanosi_temp.y2015.day21;

public enum Ring implements Item {
    NONE_0(0, 0, 0),
    NONE_1(0, 0, 0),
    DAMAGE_1(25, 0, 1),
    DAMAGE_2(50, 0, 2),
    DAMAGE_3(100, 0, 3),
    DEFENSE_1(20, 1, 0),
    DEFENSE_2(40, 2, 0),
    DEFENSE_3(80, 3, 0);

    private int cost;
    private int armor;
    private int damage;

    Ring(int cost, int armor, int damage) {
        this.cost = cost;
        this.armor = armor;
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
        return armor;
    }
}
