package tzjanosi.y2015.day21;

public class Fight {
    private Character player1;
    private Character player2;

    public Fight(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String fight() {
        int roundToEliminatePlayer2 = roundToEliminate(player2, player1);
        int roundToEliminatePlayer1 = roundToEliminate(player1, player2);
        return roundToEliminatePlayer1 < roundToEliminatePlayer2 ? player2.getName() : player1.getName();
    }

    private int roundToEliminate(Character victim, Character attacker) {
        int strike = attacker.getDamage() > victim.getArmor() ? attacker.getDamage() - victim.getArmor() : 1;
        int output = victim.getHitPoints() / strike;
        if (victim.getHitPoints() % strike > 0) {
            output++;
        }
        return output;
    }

    public Character getPlayer1() {
        return player1;
    }
}
