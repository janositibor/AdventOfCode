package TZJanosi.y2015.day14;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private List<Deer> deers = new ArrayList<>();

    public Race(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        String name = words[0];
        int speed = Integer.parseInt(words[3]);
        int flightDuration = Integer.parseInt(words[6]);
        int restTime = Integer.parseInt(words[13]);
        int period = flightDuration + restTime;
        Deer deer = new Deer(name, speed, flightDuration, period);
        deers.add(deer);
    }

    public int maxDistance(int time) {
        return deers.stream()
                .mapToInt(d -> d.distanceInTime(time))
                .max()
                .getAsInt();
    }

    @Override
    public String toString() {
        return "Race{" +
                "deers=" + deers +
                '}';
    }
}
