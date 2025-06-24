package TZJanosi.y2015.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FightTest {
    @Test
    void build() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 13)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);
    }

    @Test
    void testTurn1() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 13)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);

        fight.turn(Spell.create("Poison"));

        assertEquals(null, fight.getWinner());
        assertEquals(173, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 2)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 77);
    }

    @Test
    void testTurn2() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 13)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);

        fight.turn(Spell.create("Poison"));
        fight.turn(Spell.create("MagicMissile"));
        assertEquals("Player", fight.getWinner());
        assertEquals(226, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 0)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 2)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 24);
    }

    @Test
    void testFight1() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 13)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);

        fight.fight(List.of(Spell.create("Poison"), Spell.create("MagicMissile")));
        assertEquals("Player", fight.getWinner());
        assertEquals(226, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 0)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 2)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 24);
    }

    @Test
    void testFight2() {
        Boss boss = new Boss(14);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 14)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);

        fight.fight(List.of(Spell.create("Recharge"), Spell.create("Shield"), Spell.create("Drain"), Spell.create("Poison"), Spell.create("MagicMissile")));
        assertEquals("Player", fight.getWinner());
        assertEquals(641, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", -1)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 1)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 114);
    }

    @Test
    void testFightForCorrectMatchingDurableSpells1() {
        Boss boss = new Boss(23);
        Player player = new Player(100, 300);
        Fight fight = new Fight(boss, player);

        fight.fight(List.of(Spell.create("Poison"), Spell.create("MagicMissile"), Spell.create("MagicMissile")));
        assertEquals("Player", fight.getWinner());
        assertEquals(279, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 0)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 84)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 21);
    }

    @Test
    void testFightForCorrectMatchingDurableSpells2() {
        Boss boss = new Boss(73);
        Player player = new Player(100, 1000);
        Fight fight = new Fight(boss, player);

        fight.fight(List.of(Spell.create("Poison"), Spell.create("MagicMissile"), Spell.create("MagicMissile")
                , Spell.create("Poison"), Spell.create("MagicMissile"), Spell.create("MagicMissile")
                , Spell.create("Poison"), Spell.create("MagicMissile"), Spell.create("MagicMissile")));
        assertEquals("Player", fight.getWinner());
        assertEquals(837, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", -2)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 36)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 163);
    }

    @Test
    void testTurn1Part2() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 13)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);

        fight.turn(Spell.create("Poison"), true);

        assertEquals(null, fight.getWinner());
        assertEquals(173, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 1)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 77);
    }

    @Test
    void testTurn2Part2() {
        Boss boss = new Boss(13);
        Player player = new Player(10, 250);
        Fight fight = new Fight(boss, player);

        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 13)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 250);

        fight.turn(Spell.create("Poison"), true);
        fight.turn(Spell.create("MagicMissile"), true);
        assertEquals("Boss", fight.getWinner());
        assertEquals(173, fight.getCost());
        assertThat(fight.getBoss())
                .hasFieldOrPropertyWithValue("hitPoints", 10)
                .hasFieldOrPropertyWithValue("damage", 8)
                .hasFieldOrPropertyWithValue("armor", 0);
        assertThat(fight.getPlayer())
                .hasFieldOrPropertyWithValue("hitPoints", 0)
                .hasFieldOrPropertyWithValue("damage", 0)
                .hasFieldOrPropertyWithValue("armor", 0)
                .hasFieldOrPropertyWithValue("mana", 77);
    }
}