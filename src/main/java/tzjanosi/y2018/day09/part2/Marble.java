package tzjanosi.y2018.day09.part2;

public class Marble {
    private int value;
    private Marble left;
    private Marble right;

    public Marble(int value) {
        this.value = value;
        this.right = this;
        this.left = this;
    }

    public Marble remove() {
        left.right = right;
        right.left = left;
        return right;
    }

    public Marble add(Marble marbleToAdd) {
        Marble previousRight = right;
        right = marbleToAdd;
        marbleToAdd.left = this;
        marbleToAdd.right = previousRight;
        previousRight.left = marbleToAdd;
        return marbleToAdd;
    }

    public int getValue() {
        return value;
    }

    public Marble getLeft() {
        return left;
    }

    public Marble getRight() {
        return right;
    }
}
