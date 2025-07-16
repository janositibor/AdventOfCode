package tzjanosi.y2015.day09;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Way {
    private Set<String> locations = new TreeSet<>();
    private int distance;

    public Way(String location1, String location2, int distance) {
        locations.add(location1);
        locations.add(location2);
        this.distance = distance;
    }

    public boolean containsLocations(String locationToCheck1, String locationToCheck2) {
        return locations.contains(locationToCheck1) && locations.contains(locationToCheck2);
    }

    public boolean containsLocation(String locationToCheck) {
        return locations.contains(locationToCheck);
    }

    public Set<String> getLocations() {
        return locations;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Way way = (Way) o;
        return distance == way.distance && Objects.equals(locations, way.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locations, distance);
    }

    @Override
    public String toString() {
        return "Way{" +
                "locations=" + locations +
                ", distance=" + distance +
                "}\n";
    }
}
