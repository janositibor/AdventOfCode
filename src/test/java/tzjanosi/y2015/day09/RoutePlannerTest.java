package tzjanosi.y2015.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoutePlannerTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        RoutePlanner planner = new RoutePlanner(readData.getOutput());
        assertThat(planner.getWays())
                .hasSize(3)
                .containsExactly(new Way("Dublin", "London", 464),
                        new Way("Belfast", "London", 518),
                        new Way("Belfast", "Dublin", 141)
                );
        assertThat(planner.getDestinations())
                .hasSize(3)
                .contains("Dublin", "London", "Belfast");
    }
}