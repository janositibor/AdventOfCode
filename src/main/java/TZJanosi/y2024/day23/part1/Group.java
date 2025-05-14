package TZJanosi.y2024.day23.part1;

import java.util.Objects;
import java.util.Set;

public class Group {
    private Set<String> computers;

    public Group(Set<String> computers) {
        this.computers = computers;
    }

    public Group(String computer1, String computer2, String computer3) {
        this.computers = Set.of(computer1, computer2, computer3);
    }

    public boolean containsConnection() {
        return false;
    }

    public boolean containsComputerWithPrefix(String prefix) {
        return computers.stream().anyMatch(c -> c.startsWith(prefix));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(computers, group.computers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(computers);
    }

    @Override
    public String toString() {
        return "Group{" +
                "computers=" + computers +
                '}';
    }
}
