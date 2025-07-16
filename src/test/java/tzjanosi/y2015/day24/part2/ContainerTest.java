package tzjanosi.y2015.day24.part2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {
    @Test
    void containerValidTest() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4);
        Container container = new Container();
        for (int i = 0; i < values.size(); i++) {
            container.addPackage(values.get(i));
        }
        assertEquals(10, container.getWeight());
        assertEquals(4, container.getNumberOfPackages());
        assertEquals(24, container.getQe());
        assertThat(container.getPackages())
                .containsOnly(1, 2, 3, 4);
    }

    @Test
    void constructorWithSet() {
        Set<Integer> values = Set.of(1, 2, 3, 4);
        Container container = new Container(values);
        assertEquals(10, container.getWeight());
        assertEquals(4, container.getNumberOfPackages());
        assertEquals(24, container.getQe());
        assertThat(container.getPackages())
                .containsOnly(1, 2, 3, 4);
    }
}