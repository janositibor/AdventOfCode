package tzjanosi_temp.y2024.day25;

import java.util.ArrayList;
import java.util.List;

public class Organizer {
    private List<Key> keys = new ArrayList<>();
    private List<Lock> locks = new ArrayList<>();
    private List<String> input;

    public Organizer(List<String> input) {
        this.input = input;
        buildOrganizer();
    }

    public long countOfCompatiblePairs() {
        return locks.stream().mapToLong(l -> keys.stream().filter(l::compatible).count()).sum();
    }

    private void buildOrganizer() {
        for (int i = 0; i < input.size(); i += 8) {
            String line = input.get(i);
            if (line.charAt(0) == '#') {
                Key key = createKey(i);
                key.setId(keys.size() + 1);
                keys.add(key);
            } else {
                Lock lock = createLock(i);
                lock.setId(locks.size() + 1);
                locks.add(lock);
            }
        }
    }

    private Lock createLock(int index) {
        char keyCharacter = '#';
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            boolean foundHeight = false;
            int row = 1;
            while (!foundHeight && row <= 5) {
                if (input.get(index + row).charAt(i) == keyCharacter) {
                    heights.add(5 + 1 - row);
                    foundHeight = true;
                }
                row++;
            }
            if (!foundHeight) {
                heights.add(5 + 1 - row);
            }
        }
        return new Lock(heights);
    }

    private Key createKey(int index) {
        char keyCharacter = '.';
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            boolean foundHeight = false;
            int row = 1;
            while (!foundHeight && row <= 5) {
                if (input.get(index + row).charAt(i) == keyCharacter) {
                    heights.add(row - 1);
                    foundHeight = true;
                }
                row++;
            }
            if (!foundHeight) {
                heights.add(row - 1);
            }
        }
        return new Key(heights);
    }


    public List<Key> getKeys() {
        return keys;
    }

    public List<Lock> getLocks() {
        return locks;
    }
}
