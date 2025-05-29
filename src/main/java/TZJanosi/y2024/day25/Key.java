package TZJanosi.y2024.day25;

import java.util.ArrayList;
import java.util.List;

public class Key {
    private int id;
    private List<Integer> heights = new ArrayList<>();

    public Key(List<Integer> heights) {
        this.heights = heights;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", heights=" + heights +
                '}';
    }

    public List<Integer> getHeights() {
        return heights;
    }
}
