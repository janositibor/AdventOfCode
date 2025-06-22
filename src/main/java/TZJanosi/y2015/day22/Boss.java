package TZJanosi.y2015.day22;

public class Boss extends Character {

    public Boss(int hitPoints) {
        super(hitPoints, 8, 0);
    }

    public Boss(Boss original) {
        super(original.getHitPoints(), 8, 0);
    }


    public void attack(Character opponent) {
        int damage = Math.max(1, getDamage() - opponent.getArmor());
        opponent.setHitPoints(opponent.getHitPoints() - damage);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
