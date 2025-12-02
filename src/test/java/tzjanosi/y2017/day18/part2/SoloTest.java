package tzjanosi.y2017.day18.part2;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SoloTest {
    @Test
    void initTest() {
        ReadData readData = new ReadData("input.txt");
        Connection connection = new Connection(new LinkedBlockingQueue<Long>(), new LinkedBlockingQueue<Long>(), List.of(false), List.of(false), List.of(false), List.of(false), List.of(0));
        Solo solo0 = new Solo(0, readData.getOutput(), connection);
        assertEquals(0L, (long) solo0.getRegisters().stream().filter(r -> "p".equals(r.getName())).map(r -> r.getValue()).findFirst().get());
        assertThat(solo0.getRegisters())
                .hasSize(6)
                .contains(new Register("one", 1L))
                .contains(new Register("p", 0L));
        Solo solo1 = new Solo(1, readData.getOutput(), connection);
        assertEquals(1L, (long) solo1.getRegisters().stream().filter(r -> "p".equals(r.getName())).map(r -> r.getValue()).findFirst().get());
        assertThat(solo1.getRegisters())
                .hasSize(6)
                .contains(new Register("one", 1L))
                .contains(new Register("p", 1L));
    }

}