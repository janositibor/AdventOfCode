package TZJanosi.y2015.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Arena {
    private Character boss = new Character("Boss", 104, 8, 1);
    private Character you = new Character("You", 100, 0, 0);
    private Fight fight = new Fight(you, boss);
    private Map<Integer, Set<Item>> winnerSets = new HashMap<>();
    private Map<Integer, Set<Item>> loserSets = new HashMap<>();

    private void findSets() {
        Item[] weapons = Weapon.values();
        Item[] armors = Armor.values();
        Item[] rings = Ring.values();
        for (int i = 0; i < weapons.length; i++) {
            for (int j = 0; j < armors.length; j++) {
                for (int k = 0; k < rings.length; k++) {
                    for (int l = k + 1; l < rings.length; l++) {
                        Item weapon = weapons[i];
                        you.addItem(weapon);
                        Item armor = armors[j];
                        you.addItem(armor);
                        Item ring1 = rings[k];
                        you.addItem(ring1);
                        Item ring2 = rings[l];
                        you.addItem(ring2);

                        if (fight.fight().equals("You")) {
                            winnerSets.put(you.getCost(), you.getItems());
                        } else {
                            loserSets.put(you.getCost(), you.getItems());
                        }
                        fight.getPlayer1().reset();
                    }
                }
            }
        }
    }

    public int findCheapestWinner() {
        if (winnerSets.isEmpty()) {
            findSets();
        }
//        System.out.println(winnerSets);
        return winnerSets.entrySet()
                .stream()
                .mapToInt(e -> e.getKey())
                .min()
                .orElseThrow(() -> new IllegalStateException("Empty items set!"));
    }

    public int findMostExpensiveLoser() {
        if (loserSets.isEmpty()) {
            findSets();
        }
//        System.out.println(loserSets);
        return loserSets.entrySet()
                .stream()
                .mapToInt(e -> e.getKey())
                .max()
                .orElseThrow(() -> new IllegalStateException("Empty items set!"));
    }
}
