package tzjanosi.y2025.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Region {
    private int width;
    private int length;
    private int area;
    private static final int CELL_SIZE = 3;
    private List<Integer> presentCounts = new ArrayList<>();
    private Set<Present> presents;

    public Region(int width, int length) {
        this.width = width;
        this.length = length;
        area = width * length;
    }

    public void addPresentCount(int count) {
        presentCounts.add(count);
    }

    public int zeroVersionCheck() {
        if (thereIsEnoughCellForAllPresent()) {
            return 1;
        }
        if (thereIsNotEnoughSpaceForAllPresent()) {
            return -1;
        }
        return 0;
    }

    private boolean thereIsNotEnoughSpaceForAllPresent() {
        return totalSizeOfPresents() > area;
    }

    private boolean thereIsEnoughCellForAllPresent() {
        return totalCountOfPresents() <= getCellNumber();
    }

    private int totalCountOfPresents() {
        return presentCounts.stream().mapToInt(Integer::intValue).sum();
    }

    private int totalSizeOfPresents() {
        int output = 0;
        for (int i = 0; i < presentCounts.size(); i++) {
            output += (getPresentById(i).getSize() * presentCounts.get(i));
        }
        return output;
    }

    private Present getPresentById(int id) {
        return presents.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("No present found with index: %d", id)));
    }

    private int getCellNumber() {
        return (length / CELL_SIZE) * (width / CELL_SIZE);
    }

    public void setPresents(Set<Present> presents) {
        this.presents = presents;
    }
}
