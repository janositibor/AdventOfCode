package tzjanosi.y2017.day15;

public class Generator {
    private long divider = 2147483647L;
    private int multiplicator;


    public Generator(int multiplicator) {
        this.multiplicator = multiplicator;
    }

    public long generate(long from) {

        return (from * multiplicator) % divider;
    }
}
