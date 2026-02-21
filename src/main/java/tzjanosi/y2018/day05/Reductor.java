package tzjanosi.y2018.day05;

public class Reductor {
    private String polymer;
    private int distance = Math.abs('A' - 'a');

    public Reductor(String polymer) {
        this.polymer = polymer;
    }

    public int reduce() {
        StringBuilder tempPolymer = new StringBuilder(polymer);
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
        return tempPolymer.length();
    }
}
