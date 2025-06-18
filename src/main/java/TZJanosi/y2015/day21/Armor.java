package TZJanosi.y2015.day21;

public enum Armor implements Item {
    None(0, 0),
    Leather(13, 1),
    Chainmail(31, 2),
    Splintmail(53, 3),
    Bandedmail(75, 4),
    Platemail(102, 5);

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
