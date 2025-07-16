package tzjanosi.y2015.day21;

public enum Armor implements Item {
    NONE(0, 0),
    LEATHER(13, 1),
    CHAINMAIL(31, 2),
    SPLINTMAIL(53, 3),
    BANDEDMAIL(75, 4),
    PLATEMAIL(102, 5);

    private int cost;
    private int armor;

    Armor(int cost, int armor) {
        this.cost = cost;
        this.armor = armor;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getArmor() {
        return armor;
    }
}
