package tzjanosi.y2024.day23;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {
    private Set<String> computers;

    public Group(Set<String> computers) {
        this.computers = computers;
    }

    public Group(String computer1, String computer2, String computer3) {
        this.computers = Set.of(computer1, computer2, computer3);
    }

    public String getPassword() {
        return computers.stream().sorted(Comparator.naturalOrder()).collect(Collectors.joining(","));
    }

    public boolean containsConnection(Connection connection) {
        Set<String> computersToCheck = connection.getComputers();
        for (String computer : computersToCheck) {
            if (containsComputer(computer)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsComputer(String computer) {
        return computers.stream().anyMatch(c -> c.equals(computer));
    }

    public boolean containsComputerWithPrefix(String prefix) {
        return computers.stream().anyMatch(c -> c.startsWith(prefix));
    }

    public void addComputer(String computer) {
        computers.add(computer);
    }

    public Set<String> getComputers() {
        return computers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
