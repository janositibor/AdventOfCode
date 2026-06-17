package tzjanosi.y2018.day24;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Army {
    private String nation;
    private int unitNumber;
    private int unitsHitPoint;
    private List<String> immunities = new ArrayList<>();
    private List<String> weaknesses = new ArrayList<>();
    private String attackType;
    private int damage;
    private int initiative;

    public Army(String nation, int unitNumber, int unitsHitPoint, String attackType, int damage, int initiative) {
        this.nation = nation;
        this.unitNumber = unitNumber;
        this.unitsHitPoint = unitsHitPoint;
        this.attackType = attackType;
        this.damage = damage;
        this.initiative = initiative;
    }

    public int attackedBy(Army other) {
        int damage = damageBy(other);
        int lostunits = damage / unitsHitPoint;
        unitNumber -= lostunits;
        return unitNumber;
    }

    public int damageBy(Army other) {
        int damage = other.getEffectivePower();
        if (immunities.contains(other.attackType)) {
            damage = 0;
        }
        if (weaknesses.contains(other.attackType)) {
            damage *= 2;
        }
        return damage;
    }

    public int getEffectivePower() {
        return unitNumber * damage;
    }

    public void setImmunities(List<String> immunities) {
        this.immunities = immunities;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getNation() {
        return nation;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public int getInitiative() {
        return initiative;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Army army = (Army) o;
        return unitsHitPoint == army.unitsHitPoint && damage == army.damage && initiative == army.initiative && Objects.equals(nation, army.nation) && Objects.equals(immunities, army.immunities) && Objects.equals(weaknesses, army.weaknesses) && Objects.equals(attackType, army.attackType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nation, unitsHitPoint, immunities, weaknesses, attackType, damage, initiative);
    }

    @Override
    public String toString() {
        return "Army{" +
                "nation='" + nation + '\'' +
                ", unitNumber=" + unitNumber +
                ", damage=" + damage +
                ", initiative=" + initiative +
                ", effectivePower=" + getEffectivePower() +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Army{" +
//                "nation='" + nation + '\'' +
//                ", unitNumber=" + unitNumber +
//                ", unitsHitPoint=" + unitsHitPoint +
//                ", immunities=" + immunities +
//                ", weaknesses=" + weaknesses +
//                ", attackType='" + attackType + '\'' +
//                ", damage=" + damage +
//                ", initiative=" + initiative +
//                '}';
//    }
}


