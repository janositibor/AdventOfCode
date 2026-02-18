package tzjanosi.y2018.day04;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Dairy {
    private List<Shift> shifts = new ArrayList<>();
    private Optional<Shift> shiftForInit = Optional.empty();
    private Optional<Sleep> sleepForInit = Optional.empty();

    public Dairy(List<String> input) {
        processInput(input.stream().sorted().toList());
    }

    public int bestChanceToSneakStrategy2() {
        Map<Map<Integer, Integer>, Integer> numberOfSleeps = buildNumberOfSleeps();
        return numberOfSleeps.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get()
                .entrySet()
                .stream()
                .mapToInt(e -> e.getKey() * e.getValue())
                .findFirst()
                .getAsInt();

    }

    private Map<Map<Integer, Integer>, Integer> buildNumberOfSleeps() {
        Map<Map<Integer, Integer>, Integer> output = new ConcurrentHashMap<>();
        for (int i = 0; i < shifts.size(); i++) {
            Shift shift = shifts.get(i);
            int guard = shift.getGuard();
            List<Integer> sleepingMinutes = shift.sleepingMinutes();
            output = arrangeMinutes(guard, output, sleepingMinutes);
        }
        return output;
    }

    private Map<Map<Integer, Integer>, Integer> arrangeMinutes(int guard, Map<Map<Integer, Integer>, Integer> input, List<Integer> sleepingMinutes) {
        Map<Map<Integer, Integer>, Integer> output = new ConcurrentHashMap<>(input);
        for (int j = 0; j < sleepingMinutes.size(); j++) {
            int actualMinute = sleepingMinutes.get(j);
            Map<Integer, Integer> key = Map.of(guard, actualMinute);
            if (!output.containsKey(key)) {
                output.put(key, 0);
            }
            output.put(key, output.get(key) + 1);
        }
        return output;
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
        if (sleepForInit != null) {
            shiftForInit.get().addSleep(sleepForInit.get());
        }
        shifts.add(shiftForInit.get());
    }

    private void processWakes(int minute) {
        int to = minute;
        sleepForInit.get().setTo(to);
    }

    private void processFalls(int minute) {
        if (sleepForInit.isPresent()) {
            shiftForInit.get().addSleep(sleepForInit.get());
        }
        int from = minute;
        sleepForInit = Optional.of(new Sleep(from));
    }

    private void processGuard(String info, String date) {
        if (shiftForInit.isPresent()) {
            if (sleepForInit.isPresent()) {
                shiftForInit.get().addSleep(sleepForInit.get());
            }
            shifts.add(shiftForInit.get());
        }
        int guard = guardFromInfo(info);
        shiftForInit = Optional.of(new Shift(date, guard));
        sleepForInit = Optional.empty();
    }

    private int guardFromInfo(String info) {
        Pattern pattern = Pattern.compile("Guard\\s+#(\\d+)\\s+begins\\s+shift");
        Matcher m = pattern.matcher(info);
        m.find();
        return Integer.parseInt(m.group(1));
    }
}
