package TZJanosi.y2024.day23;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Connection {
    private Set<String> computers = new HashSet<>();

    public Connection(String computer1, String computer2) {
        if (computer1.equals(computer2)) {
            throw new IllegalArgumentException(String.format("The two computers seem to be identical: %s vs. %s", computer1, computer2));
        }
        computers.add(computer1);
        computers.add(computer2);
    }

    public boolean containsComputer(String computer) {
        return computers.contains(computer);
    }

    public List<String> getComputersAsAList() {
        return computers.stream().toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(computers, that.computers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(computers);
    }

    public Set<String> getComputers() {
        return computers;
    }
}
