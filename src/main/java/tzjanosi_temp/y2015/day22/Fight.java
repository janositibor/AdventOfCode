package tzjanosi_temp.y2015.day22;

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

    public void turn(Spell spell, boolean hard) {
        if (hard) {
            player.setHitPoints(player.getHitPoints() - 1);
        }
        if (isAlive(player)) {
            turn(spell);
        } else {
            winner = "Boss";
        }
    }

    public void fight(List<Spell> spells) {
        for (int i = 0; i < spells.size(); i++) {
            Spell spell = spells.get(i);
            turn(spell);
        }
    }

    public void turn(Spell spell) {
        playerAttack();

        if (player.hasMana()) {
            cost += player.buy(spell);
            playerAttack();
            if (isAlive(boss)) {
                bossAttack();
                if (!isAlive(player)) {
                    winner = "Boss";
                }
            } else {
                winner = "Player";
            }
        } else {
            winner = "Boss";
        }
    }

    private boolean isAlive(Character character) {
        return character.getHitPoints() > 0;
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
