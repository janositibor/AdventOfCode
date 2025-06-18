package TZJanosi.y2015.day21;

public enum Ring implements Item {
    None0(0, 0, 0),
    None1(0, 0, 0),
    Damage1(25, 0, 1),
    Damage2(50, 0, 2),
    Damage3(100, 0, 3),
    Defense1(20, 1, 0),
    Defense2(40, 2, 0),
    Defense3(80, 3, 0);

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
