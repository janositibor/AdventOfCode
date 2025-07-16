package tzjanosi_temp.y2015.day23;

public class Register {
    private String name;
    private long value;

//    public Register(String name) {
//        this.name = name;
//    }

    public Register(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public int hlf() {
        value /= 2;
        return 1;
    }

    public int tpl() {
        value *= 3;
        return 1;
    }

    public int inc() {
        value += 1;
        return 1;
    }

    public int jmp(int offset) {
        return offset;
    }

    public int jie(int offset) {
        return (value % 2 == 0 ? offset : 1);
    }

    public int jio(int offset) {
        return (value == 1 ? offset : 1);
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }
}
