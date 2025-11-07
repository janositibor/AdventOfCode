package tzjanosi.y2017.day15;

public class Generator {
    private long divider = 2147483647L;
    private int pickyCriterion;
    private int multiplicator;


    public Generator(int multiplicator) {
        this.multiplicator = multiplicator;
    }

    public Generator(int multiplicator, int pickyCriterion) {
        this.pickyCriterion = pickyCriterion;
        this.multiplicator = multiplicator;
    }

    public long generate(long from) {
        return (from * multiplicator) % divider;
    }

    public long generatePicky(long from) {
        long tempResult = (from * multiplicator) % divider;
        return tempResult % pickyCriterion == 0 ? tempResult : generatePicky(tempResult);
    }
}
