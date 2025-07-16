package tzjanosi_temp.y2015.day22;

import java.util.Objects;

public class Spell {
    private String name;
    private int cost;
    private int damage;
    private int armor;
    private int heal;
    private int last;
    private int mana;

    public Spell(int cost, int damage, int armor, int heal, int mana, int last) {
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
        this.heal = heal;
        this.mana = mana;
        this.last = last;
    }

    public Spell(Spell original) {
        name = original.name;
        cost = original.cost;
        damage = original.damage;
        armor = original.armor;
        heal = original.heal;
        mana = original.mana;
        last = original.last;
    }

    public static Spell create(String name) {
        Spell output;
        switch (name) {
            case "MagicMissile":
                output = new Spell(53, 4, 0, 0, 0, 1);
                break;
            case "Drain":
                output = new Spell(73, 2, 0, 2, 0, 1);
                break;
            case "Shield":
                output = new Spell(113, 0, 7, 0, 0, 6);
                break;
            case "Poison":
                output = new Spell(173, 3, 0, 0, 0, 6);
                break;
            case "Recharge":
                output = new Spell(229, 0, 0, 0, 101, 5);
                break;
            default:
                throw new IllegalArgumentException(String.format("No spell found with name: %s", name));
        }
        output.setName(name);
        return output;
    }

    public void cast(Player attacker, Boss attacked) {
        if (damage > 0) {
            attacked.setHitPoints(attacked.getHitPoints() - damage);
        }
        if (armor > 0 && last == 6) {
            attacker.setArmor(attacker.getArmor() + armor);
        }
        if (heal > 0) {
            attacker.setHitPoints(attacker.getHitPoints() + heal);
        }
        if (mana > 0) {
            attacker.setMana(attacker.getMana() + mana);
        }

        last = Math.max(0, last - 1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getArmor() {
        return armor;
    }

    public int getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", last=" + last +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spell spell = (Spell) o;
        return Objects.equals(name, spell.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
