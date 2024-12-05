package TZJanosi.y2024.day05;

import java.util.ArrayList;
import java.util.List;

public class ListInList {
    public static List<List<Integer>> deepClone(List<List<Integer>> original) {
        List<List<Integer>> cloned = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            List<Integer> innerList=original.get(i);
            cloned.add(new ArrayList<>(innerList));
        }
        return cloned;
    }
}
