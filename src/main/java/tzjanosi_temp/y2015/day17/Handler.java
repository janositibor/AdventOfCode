package tzjanosi_temp.y2015.day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Handler {
    private Container start;
    private Set<Set<Bucket>> validFilling = new HashSet<>();
    private int requiredVolume;
    private List<Bucket> buckets;

    public Handler(int requiredVolume, List<String> input) {
        this.requiredVolume = requiredVolume;
        buckets = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Bucket bucket = new Bucket(i, Integer.parseInt(input.get(i)));
            buckets.add(bucket);
        }
        start = new Container(buckets);
    }

    public int findNumbersOfCombinationWithMinimalBucketNumbers() {
        buildValidFilling(start, 0);
        int minimalNumberOfBuckets = getMinimalNumberOfBucketsInValidFilling();
        return (int) validFilling.stream().filter(s -> s.size() == minimalNumberOfBuckets).count();
    }

    private int getMinimalNumberOfBucketsInValidFilling() {
        return validFilling.stream().mapToInt(Set::size).min().getAsInt();
    }

    public int findNumbersOfValidFilling() {
        buildValidFilling(start, 0);
        return validFilling.size();
    }

    private void buildValidFilling(Container actual, int from) {
        int actualVolume = actual.getTotalVolume();
        if (actualVolume > requiredVolume) {
            return;
        }
        if (actualVolume == requiredVolume) {
            validFilling.add(actual.getFilled());
            return;
        }
        if (from < buckets.size()) {
            for (int i = from; i < buckets.size(); i++) {
                int id = buckets.get(i).getId();
                Container next = new Container(actual);
                next.fill(id);
                buildValidFilling(next, i + 1);
            }
        }
    }

    public Container getStart() {
        return start;
    }
}
