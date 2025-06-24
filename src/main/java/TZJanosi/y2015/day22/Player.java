package TZJanosi.y2015.day22;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player extends Character {
    private int mana;
    private Set<Spell> spells = new HashSet<>();
    private List<Spell> appliedSpells = new ArrayList<>();

    public Player(int hitPoints, int mana) {
        super(hitPoints, 0, 0);
        this.mana = mana;
    }

    public Player(Player original) {
        super(original.getHitPoints(), original.getDamage(), original.getArmor());
        mana = original.getMana();
        spells = new HashSet<>(original.getSpells().stream().map(s -> new Spell(s)).toList());
        appliedSpells = new ArrayList<>(original.getAppliedSpells());
    }

    private boolean spellsContains(Spell spell) {
        return spells.stream().anyMatch(s -> s.getName().equals(spell.getName()));
    }

    public void attack(Boss opponent) {
        for (Spell spell : spells) {
            spell.cast(this, opponent);
        }
        removeDeprecated();
    }

    private void removeDeprecated() {
        Spell shield = Spell.create("Shield");
        if (spells.stream().anyMatch(s -> (s.getLast() == 0 && s.getName().equals(shield.getName())))) {
            setArmor(getArmor() - shield.getArmor());
        }
        spells.removeIf(s -> s.getLast() == 0);
    }

    public int buy(Spell spell) {
        if (spellsContains(spell)) {
            throw new IllegalArgumentException(String.format("The spell (%s) is already in the spells list!", spell.getName()));
        }
        if (getMana() < spell.getCost()) {
            throw new IllegalStateException(String.format("Not having enough Mana (%d) to buy spell(%s), for: %d", getMana(), spell.getName(), spell.getCost()));
        }
        setMana(getMana() - spell.getCost());
        spells.add(spell);
        appliedSpells.add(spell);
        return spell.getCost();
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Set<Spell> getSpells() {
        return spells;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hitPoints=" + super.getHitPoints() +
                ", armor=" + super.getArmor() +
                ", mana=" + mana +
                ", spells=" + spells +
                '}';
    }

    public boolean hasMana() {
        return mana >= 53;
    }

    public List<Spell> getAppliedSpells() {
        return appliedSpells;
    }
}
