package tzjanosi_temp.y2015.day22;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private List<Spell> spells = List.of(
            Spell.create("MagicMissile")
            , Spell.create("Drain")
            , Spell.create("Shield")
            , Spell.create("Poison")
            , Spell.create("Recharge")
    );
    private int minCost = Integer.MAX_VALUE;
    private List<Spell> cheapestSpells;

    public void findMinCostToWin(Fight fight, boolean part2) {
        if (fight.getCost() > minCost) {
            return;
        }
        String winner = fight.getWinner();
        if (winner != null) {
            if ("Player".equals(winner)) {
                setWinner(fight);
            }
            return;
        }
        List<Spell> availableSpells = availableSpells(fight);
        for (int i = 0; i < availableSpells.size(); i++) {
            Spell spellToUse = Spell.create(availableSpells.get(i).getName());
            Fight next = new Fight(fight);
            if (part2) {
                next.turn(spellToUse, true);
            } else {
                next.turn(spellToUse);
            }
            findMinCostToWin(next, part2);
        }
    }

    private List<Spell> availableSpells(Fight fight) {
// This section is commented out because the stream version (below) is more compact, but probably less readable.
//        Set<Spell> actualSpells=fight.getPlayer().getSpells();
//        Set<Spell> notAvailableSpells=new HashSet<>();
//        for (Spell spell:actualSpells){
//            if(spell.getLast()>1){
//                notAvailableSpells.add(spell);
//            }
//        }
//        for (Spell spell:spells) {
//            if (spell.getCost() > fight.getPlayer().getMana()) {
//                notAvailableSpells.add(spell);
//            }
//        }
//        List<Spell> output=new ArrayList<>(spells);
//        output.removeAll(notAvailableSpells);
//
        return spells.stream()
                .filter(s -> (
                                fight.getPlayer().getSpells().stream().filter(spell -> spell.getLast() > 1).noneMatch(spell -> spell.getName().equals(s.getName()))
                                        && s.getCost() <= fight.getPlayer().getMana()
                        )
                )
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
