package tzjanosi.y2025.day05;

import java.util.Optional;
import java.util.Set;

public final class RangeHandler {

    private RangeHandler() {
    }

    static public Optional<Range> findOverLappingRange(Range rangeToCheck, Set<Range> oldRanges) {
        return oldRanges.stream().filter(o -> overlap(rangeToCheck, o)).findFirst();
    }

    static public boolean overlap(Range a, Range b) {
        return lowerOverLap(a, b) || lowerOverLap(b, a);
    }

    static private boolean lowerOverLap(Range a, Range b) {
        return a.getFrom() <= b.getFrom() && b.getFrom() <= a.getTo();
    }

    static public Range union(Range a, Range b) {
        return new Range(Math.min(a.getFrom(), b.getFrom()), Math.max(a.getTo(), b.getTo()));
    }
}
