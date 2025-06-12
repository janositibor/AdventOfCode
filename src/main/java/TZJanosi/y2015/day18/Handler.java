package TZJanosi.y2015.day18;

import java.util.List;
import java.util.Map;

public class Handler {
    Grid actual;
    Grid next;
    int numberOfSteps;

    public Handler(int numberOfSteps, List<String> input) {
        this.numberOfSteps = numberOfSteps;
        actual = new Grid(input);
    }

    public int numberOfActiveBulbsAterAnimation() {
        animation();
        return actual.countActiveBulbs();
    }

    private void animation() {
        for (int i = 0; i < numberOfSteps; i++) {
            System.out.println(i);
            step();
        }
    }

    private void step() {
        next = new Grid(actual);
        for (Map.Entry<Coordinate, Bulb> entry : next.getBulbs().entrySet()) {
            stepBulb(entry);
        }
        actual = new Grid(next);
    }

    private void stepBulb(Map.Entry<Coordinate, Bulb> entry) {
        Coordinate coordinate = entry.getKey();
        Bulb bulb = actual.getBulbs().get(coordinate);
        if (actual.hasToToggle(coordinate, bulb.isOn())) {
            entry.getValue().toggle();
        }
    }

    public Grid getActual() {
        return actual;
    }
}
