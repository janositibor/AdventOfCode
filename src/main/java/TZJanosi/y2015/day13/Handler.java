package TZJanosi.y2015.day13;

import java.util.List;

public class Handler {
    private List<String> input;
    private int maximalHappiness = Integer.MIN_VALUE;

    public Handler(List<String> input) {
        this.input = input;
    }

    public int findHappiestWayWithYou() {
        TablePlanner start = new TablePlanner(input);
        start.addNewPlayer();
        buildHappiestWay(start);
        return maximalHappiness;
    }

    public int findHappiestWay() {
        TablePlanner start = new TablePlanner(input);
        buildHappiestWay(start);
        return maximalHappiness;
    }

    private void buildHappiestWay(TablePlanner actual) {
        int actualHappiness = actual.getHappiness();

        List<String> lookForPlaces = actual.getLookForPlace();
        if (lookForPlaces.isEmpty()) {
            if (actualHappiness > maximalHappiness) {
                maximalHappiness = actualHappiness;
            }
            return;
        }
        for (int i = 0; i < lookForPlaces.size(); i++) {
            TablePlanner next = new TablePlanner(actual);
            String name = lookForPlaces.get(i);
            next.sit(name);
            buildHappiestWay(next);
        }
    }
}
