package tzjanosi.y2016.day11;

import java.util.Objects;

public class Person {
    private String id;
    private boolean man;
    private int level;

    public Person(String id, boolean man, int level) {
        this.id = id;
        this.man = man;
        this.level = level;
    }

    public Person(Person original) {
        id = original.id;
        man = original.man;
        level = original.level;
    }

    public void moveUp() {
        if (level == 4) {
            throw new IllegalStateException(String.format("%s %s can not move up. Already at level 4", id, man ? "man" : "woman"));
        }
        level++;
    }

    public void moveDown() {
        if (level == 1) {
            throw new IllegalStateException(String.format("%s %s can not move down. Already at level 1", id, man ? "man" : "woman"));
        }
        level--;
    }

    public int getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }

    public boolean isMan() {
        return man;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return man == person.man && level == person.level && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, man, level);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", man=" + man +
                ", level=" + level +
                '}';
    }
}
