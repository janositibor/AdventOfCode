package TZJanosi.y2024.day06;

import java.util.Arrays;

public class Trio {
    private Integer[] value;

    public Trio(Integer[] value) {
        this.value=new Integer[value.length];
        for (int i = 0; i < this.value.length; i++) {
            this.value[i] = value[i];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trio trio = (Trio) o;
        return Arrays.equals(value, trio.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    public Integer[] getValue() {
        return value;
    }
}
