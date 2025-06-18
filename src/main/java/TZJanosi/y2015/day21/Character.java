package TZJanosi.y2015.day21;

import java.util.HashSet;
import java.util.Set;

public class Character {
    private String name;
    private int hitPoints;
    private int damage;
    private int armor;
    private Set<Item> items = new HashSet<>();
    private int cost;

    public Character(String name, int hitPoints, int damage, int armor) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.armor = armor;
    }

    public void addItem(Item item) {
        items.add(item);
        damage += item.getDamage();
        armor += item.getArmor();
        cost += item.getCost();
    }

    public void reset() {
        items.clear();
        damage = 0;
        armor = 0;
        cost = 0;
    }


    public Set<Item> getItems() {
        return new HashSet<>(items);
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", damage=" + damage +
                ", armor=" + armor +
                '}';
    }
}
