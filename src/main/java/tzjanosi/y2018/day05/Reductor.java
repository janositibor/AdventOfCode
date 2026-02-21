package tzjanosi.y2018.day05;

public class Reductor {
    private String polymer;
    private int distance = Math.abs('A' - 'a');
    private String shrink;

    public Reductor(String polymer) {
        this.polymer = polymer;
    }

    public int enhancedReduce() {
        reduce();
        int output = shrink.length();
        for (int i = 'a'; i < 'z'; i++) {
            String enhancedPolymer = removeCompound(shrink, i);
            if (!enhancedPolymer.equals(shrink)) {
                int result = reduceString(enhancedPolymer).length();
                output = Math.min(output, result);
            }
        }
        return output;
    }

    public int reduce() {
        shrink = reduceString(polymer);
        return shrink.length();
    }

    private String removeCompound(String shrink, int i) {
        StringBuilder output = new StringBuilder(shrink);
        int index = 0;
        while (index < output.length()) {
            char actual = output.charAt(index);
            if (actual == i || actual == (i - distance)) {
                output.deleteCharAt(index);
            }
            index++;
        }
        return output.toString();
    }

    private String reduceString(String input) {
        StringBuilder tempPolymer = new StringBuilder(input);
        int index = 0;
        while (index < tempPolymer.length() - 1) {
            char actual = tempPolymer.charAt(index);
            char next = tempPolymer.charAt(index + 1);
            if (Math.abs(actual - next) == distance) {
                tempPolymer.delete(index, index + 2);
                if (index > 0) {
                    index--;
                }
            } else {
                index++;
            }
        }
        return tempPolymer.toString();
    }
}
