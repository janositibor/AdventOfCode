package TZJanosi.y2024.day25.part1;

import java.util.ArrayList;
import java.util.List;

public class Organizer {
    List<Key> keys = new ArrayList<>();
    List<Lock> locks = new ArrayList<>();
    List<String> input;

    public Organizer(List<String> input) {
        this.input = input;
        buildOrganizer();
    }

    public long countOfCompatiblePairs() {
        return locks.stream().mapToLong(l -> keys.stream().filter(k -> l.compatible(k)).count()).sum();
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
        Lock lock = new Lock(heights);
        return lock;
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
        Key key = new Key(heights);
        return key;
    }


    public List<Key> getKeys() {
        return keys;
    }

    public List<Lock> getLocks() {
        return locks;
    }
}
