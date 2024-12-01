package TZJanosi.y2015.day24;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {
    @Test
    void containerValidTest(){
        List<Integer> values= Arrays.asList(1,2,3,4);
        Container container=new Container();
        for (int i = 0; i < values.size(); i++) {
            container.addPackage(values.get(i));
        }
        assertEquals(10,container.getWeight());
        assertEquals(4,container.getNumberOfPackages());
        assertEquals(24,container.getQe());
        assertThat(container.getPackages())
                .containsOnly(1,2,3,4);

    }

}