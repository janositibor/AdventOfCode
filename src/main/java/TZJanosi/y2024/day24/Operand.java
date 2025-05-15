package TZJanosi.y2024.day24;

import java.util.Objects;

public class Operand {
    private String name;
    private String type = "";
    private int order;
    private boolean value;
    private int intValue;
    private boolean valid;

    public Operand(String name, int intValue) {
        this.name = name;
        createTypeAndOrderFromName();
        setValues(intValue);
    }

    public Operand(String name) {
        this.name = name;
        valid = false;
        createTypeAndOrderFromName();
    }

    private void createTypeAndOrderFromName() {
        if (Character.isDigit(name.charAt(name.length() - 1))) {
            type = name.substring(0, 1);
            order = Integer.parseInt(name.substring(1), 10);
        }
    }

    private void setValues(int intValue) {
        this.intValue = intValue;
        value = (intValue == 1);
        valid = true;
    }

    public void setValues(boolean value) {
        this.value = value;
        intValue = (value ? 1 : 0);
        valid = true;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getOrder() {
        return order;
    }

    public int getIntValue() {
        return intValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Operand operand = (Operand) o;
        return Objects.equals(name, operand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
