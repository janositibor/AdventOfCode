package tzjanosi.y2016.day12;

public enum RegisterId {
    A('a'), B('b'), C('c'), D('d');

    private char value;

    RegisterId(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
