package tzjanosi.y2017.day15;

public class Duel {
    private int moderator = 64 * 1024;
    private Generator generatorA = new Generator(16807);
    private Generator generatorB = new Generator(48271);

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

//    private String relevant16Bit(long result){
//        String binString = Long.toString(result%moderator,2);
//        binString = String.format("%16s", binString).replace(' ', '0');
//        return binString;
//    }

//    public void exec(long a, long b){
//        System.out.println("resultA: "+generatorA.generate(a));
//        System.out.println("resultB: "+generatorB.generate(b));
//        System.out.println("16Bit resultA: "+relevant16Bit(generatorA.generate(a)));
//        System.out.println("16Bit resultB: "+relevant16Bit(generatorB.generate(b)));
//        System.out.println("match: "+match(generatorA.generate(a),generatorB.generate(b)));
//        System.out.println();
//    }
}
