package TZJanosi.y2015.day22;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    @Test
    void example1() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);
        Arena arena = new Arena();

        arena.findMinCostToWin(fight);
        assertEquals(226, arena.getMinCost());
        assertThat(arena.getCheapestSpells()).containsExactly(Spell.create("Poison"),
                Spell.create("MagicMissile"));
    }

    @Test
    void example2() {
        Boss boss = new Boss(14);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);
        Arena arena = new Arena();

        arena.findMinCostToWin(fight);
        assertEquals(641, arena.getMinCost());
        assertThat(arena.getCheapestSpells()).containsExactly(Spell.create("Recharge"), Spell.create("Shield"), Spell.create("Drain"), Spell.create("Poison"), Spell.create("MagicMissile"));
    }

    @Test
    void problemData() {
        Boss boss = new Boss(55);
        Player player = new Player(50, 500);
        Fight fight = new Fight(boss, player);
        Arena arena = new Arena();

        arena.findMinCostToWin(fight);
        assertEquals(953, arena.getMinCost());
    }
}