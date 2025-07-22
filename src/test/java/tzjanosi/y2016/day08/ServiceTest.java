package tzjanosi.y2016.day08;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void runAllOperations() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(new Coordinate(7, 3), readData.getOutput());
        assertEquals(9, service.runAllOperations());
    }

    @Test
    void runAllOperationsProblemData() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(new Coordinate(50, 6), readData.getOutput());
        assertEquals(110, service.runAllOperations());
        assertThat(service.showResult())
                .hasSize(6)
                .containsExactly("****   ** *  * ***  *  *  **  ***  *    *   *  ** ",
                        "   *    * *  * *  * * *  *  * *  * *    *   *   * ",
                        "  *     * **** *  * **   *    *  * *     * *    * ",
                        " *      * *  * ***  * *  *    ***  *      *     * ",
                        "*    *  * *  * * *  * *  *  * *    *      *  *  * ",
                        "****  **  *  * *  * *  *  **  *    ****   *   **  ");
    }

}