package tzjanosi.y2015.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FightTest {

    @Test
    void fightTest() {
        Character you;
        Character boss = new Character("Boss", 12, 7, 2);
        you = new Character("You", 8, 5, 5);
        Fight fight = new Fight(you, boss);
        assertEquals("You", fight.fight());
    }

    @Test
    void test() {
        Character you;
        Character boss = new Character("Boss", 104, 8, 1);
        you = new Character("You", 100, 8, 1);
        Fight fight = new Fight(you, boss);
//        System.out.println("The Winner is: "+fight.fight());
        assertEquals("You", fight.fight());
    }

}