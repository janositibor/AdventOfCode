package tzjanosi_temp.y2015.day22;

public abstract class Character {
    private int hitPoints;
    private int damage;
    private int armor;

    public Character(int hitPoints, int damage, int armor) {
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.armor = armor;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "Character{" +
                "hitPoints=" + hitPoints +
                ", damage=" + damage +
                ", armor=" + armor +
                '}';
    }
}
