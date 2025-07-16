package tzjanosi.y2015.day13;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Couple {
    private Set<String> names = new HashSet<>();
    private int happiness;

    public Couple(String name1, String name2, int happiness) {
        names.add(name1);
        names.add(name2);
        this.happiness = happiness;
    }

    public void updateHappiness(int value) {
        happiness += value;
    }


    public boolean containsNames(String name1ToCheck, String name2ToCheck) {
        return containsName(name1ToCheck) && containsName(name2ToCheck);
    }

    public boolean containsName(String nameToCheck) {
        return names.contains(nameToCheck);
    }

    public int getHappiness() {
        return happiness;
    }

    public Set<String> getNames() {
        return names;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Couple couple = (Couple) o;
        return Objects.equals(names, couple.names);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(names);
    }

    @Override
    public String toString() {
        return "Couple{" +
                "names=" + names +
                ", hapiness=" + happiness +
                "}\n";
    }
}
