package tzjanosi_temp.y2015.day07;

import java.util.Objects;

public class Operand {
    private String name;
    private int intValue;
    private boolean valid;
    private Operand aliasFor;

    public Operand(String name, int intValue) {
        this.name = name;
        valid = true;
        this.intValue = intValue;
    }

    public Operand(String name) {
        this.name = name;
        valid = false;
    }

    public void setValue(Operand original) {
        aliasFor = original;
    }

    public void setValue(int intValue) {
        this.intValue = intValue;
        valid = true;
    }

    public boolean isValid() {
        if (aliasFor != null) {
            return aliasFor.isValid();
        }
        return valid;
    }

    public int getValue() {
        if (aliasFor != null) {
            return aliasFor.getValue();
        }
        return intValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operand operand = (Operand) o;
        return Objects.equals(name, operand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Operand{" +
                "name='" + name + '\'' +
                ", intValue=" + intValue +
                ", valid=" + valid +
                ", aliasFor=" + aliasFor +
                "}\n";
    }
}
