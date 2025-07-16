package tzjanosi.y2015.day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Container {
    private List<Bucket> buckets;
    private Set<Bucket> filled = new HashSet<>();

    public Container(List<Bucket> empty) {
        this.buckets = empty;
    }

    public Container(Container original) {
        buckets = new ArrayList<>(original.buckets);
        filled = new HashSet<>(original.filled);
    }

    public int getTotalVolume() {
        return filled.stream()
                .mapToInt(Bucket::getVolume)
                .sum();
    }

    public void fill(int id) {
        Bucket bucket = findBucket(id);
        filled.add(bucket);
    }

    private Bucket findBucket(int id) {
        return buckets.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No bucket found with id: %d", id)));
    }

    public List<Bucket> getBuckets() {
        return buckets;
    }

    public Set<Bucket> getFilled() {
        return filled;
    }

    @Override
    public String toString() {
        return "Container{" +
                "empty=" + buckets +
                ", filled=" + filled +
                '}';
    }
}
