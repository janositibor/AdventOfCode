package TZJanosi.y2015.day17;

import java.util.Objects;

public class Bucket {
    private int id;
    private int volume;

    public Bucket(int id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return id == bucket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id='" + id + '\'' +
                ", volume=" + volume +
                "}\n";
    }
}
