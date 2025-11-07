package tzjanosi.y2017.day15;

public class Duel {
    private int moderator = 64 * 1024;
    private Generator generatorA = new Generator(16807, 4);
    private Generator generatorB = new Generator(48271, 8);

    private boolean match(long resultA, long resultB) {
        return (resultA % moderator) == (resultB % moderator);
    }

    public int countMatch(int numberOfLoops, long initialValueA, long initialValueB) {
        int output = 0;
        long inputA = initialValueA;
        long inputB = initialValueB;
        for (int i = 0; i < numberOfLoops; i++) {
            long resultA = generatorA.generate(inputA);
            long resultB = generatorB.generate(inputB);
            if (match(resultA, resultB)) {
                output++;
            }
            inputA = resultA;
            inputB = resultB;
        }
        return output;
    }

    public int pickyCountMatch(int numberOfLoops, long initialValueA, long initialValueB) {
        int output = 0;
        long inputA = initialValueA;
        long inputB = initialValueB;
        for (int i = 0; i < numberOfLoops; i++) {
            long resultA = generatorA.generatePicky(inputA);
            long resultB = generatorB.generatePicky(inputB);
            if (match(resultA, resultB)) {
                output++;
            }
            inputA = resultA;
            inputB = resultB;
        }
        return output;
    }
}
