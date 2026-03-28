package tzjanosi.y2018.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AlignmentTest {
    @Test
    void getCloudTest() {
        ReadData readData = new ReadData("testInput.txt");
        Alignment alignment = new Alignment(readData.getOutput());
        assertEquals(3, alignment.getCloud(6));
    }

    @Test
    void getCloudProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Alignment alignment = new Alignment(readData.getOutput());
        assertThat(alignment.cloudAfterMove(10476))
                .containsExactly(
                        "#####   #        ####   #    #  #    #  #####      ###   #### ",
                        "#    #  #       #    #  ##   #  #    #  #    #      #   #    #",
                        "#    #  #       #       ##   #  #    #  #    #      #   #     ",
                        "#    #  #       #       # #  #  #    #  #    #      #   #     ",
                        "#####   #       #       # #  #  ######  #####       #   #     ",
                        "#    #  #       #  ###  #  # #  #    #  #           #   #     ",
                        "#    #  #       #    #  #  # #  #    #  #           #   #     ",
                        "#    #  #       #    #  #   ##  #    #  #       #   #   #     ",
                        "#    #  #       #   ##  #   ##  #    #  #       #   #   #    #",
                        "#####   ######   ### #  #    #  #    #  #        ###     #### "
                );
    }
}