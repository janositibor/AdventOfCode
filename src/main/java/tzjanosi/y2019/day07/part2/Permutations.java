package tzjanosi.y2019.day07.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations<T> {
    private List<T> originalList;

    public Permutations(List<T> originalList) {
        this.originalList = originalList;
    }

    public List<List<T>> permutations() {
        List<List<T>> result = new ArrayList<>();
        permute(originalList, 0, result);
        return result;
    }

    private void permute(List<T> list, int index, List<List<T>> result) {
        if (index == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, index, i);
            permute(list, index + 1, result);
            Collections.swap(list, index, i);
        }
    }
}
