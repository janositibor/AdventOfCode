package tzjanosi_temp.y2015.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Race {
    private List<Deer> deers = new ArrayList<>();
    private Map<Deer, Integer> points = new ConcurrentHashMap<>();

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
        points.put(deer, 0);
    }

    public int calculateTotalPoints(int time) {
        for (int i = 1; i <= time; i++) {
            addPointsAfterTime(i);
        }
        return getMaxPoints();
    }

    private int getMaxPoints() {
        return points.values()
                .stream()
                .mapToInt(x -> x)
                .max()
                .getAsInt();
    }

    private void addPointsAfterTime(int time) {
        List<Deer> winners = findWinners(time);

        updatePoints(winners);
    }

    private List<Deer> findWinners(int time) {
        int winnerDistance = Integer.MIN_VALUE;
        List<Deer> winners = new ArrayList<>();
        for (Deer deer : points.keySet()) {
            int actualDistance = deer.distanceInTime(time);
            if (actualDistance > winnerDistance) {
                winnerDistance = actualDistance;
                winners.clear();
            }
            if (actualDistance == winnerDistance) {
                winners.add(deer);
            }
        }
        return winners;
    }

    private void updatePoints(List<Deer> winners) {
        for (Deer deer : winners) {
            points.put(deer, points.get(deer) + 1);
        }
    }

    public int maxDistance(int time) {
        return deers.stream()
                .mapToInt(d -> d.distanceInTime(time))
                .max()
                .getAsInt();
    }

    public Map<Deer, Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Race{" +
                "deers=" + deers +
                '}';
    }
}
