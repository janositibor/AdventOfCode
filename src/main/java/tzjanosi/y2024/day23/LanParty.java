package tzjanosi.y2024.day23;

import java.util.*;

public class LanParty {
    private Set<Connection> connections = new HashSet<>();
    private List<String> computers = new ArrayList<>();
    private Set<Group> groups = new HashSet<>();
    private Set<Group> bigGroups = new HashSet<>();

    public LanParty(List<String> input) {
        processInput(input);
    }

    public String getPasswordOfBiggerGroup() {
        Group group = bigGroups.stream().max(Comparator.comparingInt(g -> g.getComputers().size())).orElseThrow(() -> new IllegalStateException("Empty bigGroups!"));
        return group.getPassword();
    }

    public void createBigGroups() {
        for (Connection connection : connections) {
            if (!bigGroupsContainsConnection(connection)) {
                Group group = createBigGroup(connection);
                bigGroups.add(group);
            }
        }
    }

    private boolean bigGroupsContainsConnection(Connection connection) {
        for (Group group : bigGroups) {
            if (group.containsConnection(connection)) {
                return true;
            }
        }
        return false;
    }

    private Group createBigGroup(Connection connection) {
        Group group = new Group(connection.getComputers());
        for (int i = 0; i < computers.size(); i++) {
            String candidate = computers.get(i);
            if (!group.containsComputer(candidate) && matchGroupAndCandidate(group, candidate)) {
                group.addComputer(candidate);
            }
        }
        return group;
    }

    private boolean matchGroupAndCandidate(Group group, String candidate) {
        for (String computer : group.getComputers()) {
            Connection connectionToCheck = new Connection(computer, candidate);
            if (!connections.contains(connectionToCheck)) {
                return false;
            }
        }
        return true;
    }

    private void processInput(List<String> input) {
        for (String line : input) {
            String[] computersInLine = line.split("-");
            addNewValues(computersInLine[0], computersInLine[1]);
        }
    }

    private void addNewValues(String computer1, String computer2) {
        addConnection(computer1, computer2);
        addComputers(computer1, computer2);
    }

    private void addComputers(String computer1, String computer2) {
        addComputer(computer1);
        addComputer(computer2);
    }

    private void addComputer(String computer) {
        if (!computers.contains(computer)) {
            computers.add(computer);
        }
    }

    private void addConnection(String computer1, String computer2) {
        Connection connection = new Connection(computer1, computer2);
        connections.add(connection);
    }

    public int countOfGroupWithPrefixT() {
        return (int) groups.stream().filter(g -> g.containsComputerWithPrefix("t")).count();
    }

    public void findGroups() {
        Set<Connection> connectionsToProcess = connections;
        while (connectionsToProcess.size() > 2) {
            Connection connection = connections.stream().findFirst().orElseThrow(() -> new IllegalStateException("Empty list!"));
            Set<Connection> connectionsToRemove = Set.of(connection);
            for (int i = 0; i < computers.size(); i++) {
                String candidate = computers.get(i);
                if (!connection.containsComputer(candidate) && !alreadyAGroup(connection, candidate)) {
                    connectionsToRemove = cresteConnectionsToRemove(connection, candidate);
                    if (connectionsToRemove.size() == 3) {
                        addToGroup(connection, candidate);
                    }
                }
            }
            connectionsToProcess.removeAll(connectionsToRemove);
        }
    }

    private Set<Connection> cresteConnectionsToRemove(Connection connection, String candidate) {
        Set<Connection> output = new HashSet<>(Set.of(connection));
        List<String> computers = connection.getComputersAsAList();
        Connection connectionToCheck1 = new Connection(computers.get(0), candidate);
        Connection connectionToCheck2 = new Connection(computers.get(1), candidate);
        if (connections.contains(connectionToCheck1) && connections.contains(connectionToCheck2)) {
            output.add(connectionToCheck1);
            output.add(connectionToCheck2);
        }
        return output;
    }

    private void addToGroup(Connection connection, String candidate) {
        Group groupToAdd = createGroup(connection, candidate);
        groups.add(groupToAdd);
    }

    private Group createGroup(Connection connection, String candidate) {
        Set<String> computersInConnection = new HashSet<>(connection.getComputers());
        computersInConnection.add(candidate);
        return new Group(computersInConnection);
    }

    private boolean alreadyAGroup(Connection connection, String candidate) {
        Group groupToCheck = createGroup(connection, candidate);
        return groups.contains(groupToCheck);
    }

    public Set<Connection> getConnections() {
        return connections;
    }

    public List<String> getComputers() {
        return computers;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Set<Group> getBigGroups() {
        return bigGroups;
    }
}
