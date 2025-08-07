package tzjanosi.y2016.day15;

import java.util.ArrayList;
import java.util.List;

public class Plaza {
    private List<Disk> disks = new ArrayList<>();

    public Plaza(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            Disk disk = processLine(input.get(i));
            disks.add(disk);
        }
    }

    private Disk processLine(String line) {
        String[] words = line.split(" ");
        int id = Integer.parseInt(words[1].substring(1));
        int slots = Integer.parseInt(words[3]);
        int startingPosition = Integer.parseInt(words[words.length - 1].substring(0, words[words.length - 1].length() - 1));
        return new Disk(id, slots, startingPosition);
    }

    private boolean allFreeAfterTime(int time) {
        return disks.stream().allMatch(d -> d.isFreeAfterTime(time));
    }

    public int findFirstEscapeTime() {
        int time = 0;
        while (!allFreeAfterTime(time)) {
            time++;
        }
        return time;
    }

    public int findFirstEscapeTimeWithAdditionalDisk() {
        disks.add(new Disk(disks.size() + 1, 11, 0));
        int time = 0;
        while (!allFreeAfterTime(time)) {
            time++;
        }
        return time;
    }

    public List<Disk> getDisks() {
        return disks;
    }
}
