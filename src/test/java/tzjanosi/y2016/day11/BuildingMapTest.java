package tzjanosi.y2016.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BuildingMapTest {
    @Test
    void create() {
        ReadData readData = new ReadData("testInput.txt");
        BuildingMap buildingMap = new BuildingMap(readData.getOutput());

        assertThat(buildingMap.getPeople())
                .hasSize(4)
                .containsExactly(
                        new Person("hydrogen", true, 2),
                        new Person("hydrogen", false, 1),
                        new Person("lithium", true, 3),
                        new Person("lithium", false, 1)
                );
    }
}