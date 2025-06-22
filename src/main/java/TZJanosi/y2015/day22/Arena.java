package TZJanosi.y2015.day22;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private List<Spell> spells = List.of(Spell.create("MagicMissile"),
            Spell.create("Drain"),
            Spell.create("Shield"),
            Spell.create("Poison"),
            Spell.create("Recharge"));
    private int minCost = Integer.MAX_VALUE;
    private List<Spell> cheapestSpells;

    public Arena() {
    }

    public void findMinCostToWin(Fight fight) {
        if (fight.getCost() > minCost) {
            return;
        }
        String winner = fight.getWinner();
        if (winner != null) {
            if (winner.equals("Player")) {
                setWinner(fight);
            }
            return;
        }
        List<Spell> availableSpells = availableSpells(fight);
        for (int i = 0; i < availableSpells.size(); i++) {
            Spell spellToUse = Spell.create(availableSpells.get(i).getName());
            Fight next = new Fight(fight);
            next.turn(spellToUse);
            findMinCostToWin(next);
        }
    }

    private List<Spell> availableSpells(Fight fight) {
        return spells.stream()
                .filter(s -> (fight.getPlayer().getSpells().stream().noneMatch(spell -> spell.getName().equals(s.getName())) && s.getCost() <= fight.getPlayer().getMana()))
                .toList();
    }

    private void setWinner(Fight fight) {
        int cost = fight.getCost();
        if (cost < minCost) {
            minCost = cost;
            cheapestSpells = new ArrayList<>(fight.getPlayer().getAppliedSpells());
        }
    }

    public int getMinCost() {
        return minCost;
    }

    public List<Spell> getCheapestSpells() {
        return cheapestSpells;
    }
}
