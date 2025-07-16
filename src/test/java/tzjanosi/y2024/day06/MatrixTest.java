package tzjanosi.y2024.day06;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void rotateCounterClockWiseCharTest() {
        char[][] matrixToRotate={{'a','b','c'},{'d','e','f'}};
        char[][] rotatedMatrix=Matrix.rotateCounterClockWiseChar(matrixToRotate);
        assertEquals(3,rotatedMatrix.length);
        assertEquals(2,rotatedMatrix[0].length);
        assertEquals(2,rotatedMatrix[rotatedMatrix.length-1].length);
        assertThat(IntStream.range(0,rotatedMatrix.length).mapToObj(n->new String(rotatedMatrix[n])).toList())
                .containsExactly("cf","be","ad");
    }
}