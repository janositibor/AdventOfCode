package tzjanosi_temp.y2015.day10;

public class Pair {
    private int count;
    private String letter;

    public Pair(int count, String letter) {
        this.count = count;
        this.letter = letter;
    }

    public int getCount() {
        return count;
    }

    public String getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "count=" + count +
                ", letter='" + letter + '\'' +
                '}';
    }
}
