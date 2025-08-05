package tzjanosi.y2016.day11;

import java.util.Objects;

public class Pair implements Comparable<Pair> {
    private String id;
    private Person man;
    private Person woman;

    public Pair(String id) {
        this.id = id;
    }

    public Pair(Person person) {
        this.id = person.getId();
        addPartner(person);
    }

    private void addPartner(Person person) {
        if (person.isMan()) {
            this.man = person;
        } else {
            this.woman = person;
        }
    }

    public void addPartnerPublic(Person person) {
        addPartner(person);
    }


    private int getManLevel() {
        return man.getLevel();
    }

    private int getWomanLevel() {
        return woman.getLevel();
    }

    @Override
    public int compareTo(Pair o) {
        int manLevelDiff = Integer.compare(getManLevel(), o.getManLevel());
        if (manLevelDiff != 0) {
            return manLevelDiff;
        }
        return Integer.compare(getWomanLevel(), o.getWomanLevel());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return Objects.equals(id, pair.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getId() {
        return id;
    }

    public Person getMan() {
        return man;
    }

    public Person getWoman() {
        return woman;
    }
}
