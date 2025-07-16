package tzjanosi.y2024.day25;

import java.util.List;

public class Lock {
    private int id;
    private List<Integer> heights;

    public Lock(List<Integer> heights) {
        this.heights = heights;
    }


    public boolean compatible(Key key) {
        for (int i = 0; i < heights.size(); i++) {
            if (heights.get(i) + key.getHeights().get(i) > 5) {
                return false;
            }
        }
        return true;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", heights=" + heights +
                '}';
    }

    public List<Integer> getHeights() {
        return heights;
    }
}
