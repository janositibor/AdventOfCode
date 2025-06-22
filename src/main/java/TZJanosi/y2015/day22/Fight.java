package TZJanosi.y2015.day22;

import java.util.List;

public class Fight {
    private Boss boss;
    private Player player;
    private String winner;
    private int cost;

    public Fight(Boss boss, Player player) {
        this.boss = boss;
        this.player = player;
    }

    public Fight(Fight original) {
        boss = new Boss(original.getBoss());
        player = new Player(original.getPlayer());
        cost = original.getCost();
    }

    public void fight(List<Spell> spells) {
        for (int i = 0; i < spells.size(); i++) {
            Spell spell = spells.get(i);
            turn(spell);
        }
    }

    public void turn(Spell spell) {
        playerAttack();
        if (isAlive(boss)) {
            if (player.hasMana()) {
                cost += player.buy(spell);
                playerAttack();
                if (isAlive(boss)) {
                    bossAttack();
                    if (!isAlive(player)) {
                        winner = "Boss";
//                        System.out.println("Player is dead!\n");
                    }
                } else {
                    winner = "Player";
//                    System.out.println("Boss is dead!\n");
                }
            } else {
                winner = "Boss";
//                System.out.println("Player lost, not having enough mana!");
            }
        } else {
            winner = "Player";
//            System.out.println("Boss is dead!\n");
        }
//        info();
    }

    private boolean isAlive(Character character) {
        return character.getHitPoints() > 0;
    }

    private void info() {
        System.out.println(boss);
        System.out.println(player);
        System.out.println();
    }

    private void playerAttack() {
        player.attack(boss);
    }

    private void bossAttack() {
        boss.attack(player);
    }

    public Boss getBoss() {
        return boss;
    }

    public Player getPlayer() {
        return player;
    }

    public String getWinner() {
        return winner;
    }

    public int getCost() {
        return cost;
    }
}
