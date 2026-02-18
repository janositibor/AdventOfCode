package tzjanosi.y2018.day04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Dairy {
    private List<Shift> shifts = new ArrayList<>();
    private Optional<Shift> shift = Optional.empty();
    private Optional<Sleep> sleep = Optional.empty();

    public Dairy(List<String> input) {
        processInput(input.stream().sorted().toList());
    }

    public int bestChanceToSneak() {
        int guard = sleepiestGuard();
        int bestMinute = sleepiestMinuteOfAGuard(guard);
        return guard * bestMinute;
    }

    public int sleepiestGuard() {
        Map<Integer, Integer> map = shifts.stream()
                .collect(Collectors.groupingBy(
                        Shift::getGuard, Collectors.summingInt(Shift::sleepingDuration)));
        return map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).get();
    }

    public int sleepiestMinuteOfAGuard(int guard) {
        Map<Integer, Long> map =
                shifts.stream()
                        .filter(s -> s.getGuard() == guard)
                        .flatMap(s -> s.sleepingMinutes().stream())
                        .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        return map.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalStateException("Empty map"));
    }


    private void processInput(List<String> input) {
        Pattern pattern = Pattern.compile("\\[(\\S+)\\s+(\\d+):(\\d+)\\]\\s*(.*)");
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            Matcher m = pattern.matcher(line);
            m.find();
            String date = m.group(1);
            int minute = Integer.parseInt(m.group(3));
            String info = m.group(4);
            if (info.contains("Guard")) {
                processGuard(info, date);
            }
            if (info.contains("falls")) {
                processFalls(minute);
            }
            if (info.contains("wakes")) {
                processWakes(minute);
            }
        }
        if (sleep != null) {
            shift.get().addSleep(sleep.get());
        }
        shifts.add(shift.get());
    }

    private void processWakes(int minute) {
        int to = minute;
        sleep.get().setTo(to);
    }

    private void processFalls(int minute) {
        if (sleep.isPresent()) {
            shift.get().addSleep(sleep.get());
        }
        int from = minute;
        sleep = Optional.of(new Sleep(from));
    }

    private void processGuard(String info, String date) {
        if (shift.isPresent()) {
            if (sleep.isPresent()) {
                shift.get().addSleep(sleep.get());
            }
            shifts.add(shift.get());
        }
        int guard = guardFromInfo(info);
        shift = Optional.of(new Shift(date, guard));
        sleep = Optional.empty();
    }

    private int guardFromInfo(String info) {
        Pattern pattern = Pattern.compile("Guard\\s+#(\\d+)\\s+begins\\s+shift");
        Matcher m = pattern.matcher(info);
        m.find();
        return Integer.parseInt(m.group(1));
    }
}
