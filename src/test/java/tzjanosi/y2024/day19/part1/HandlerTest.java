package tzjanosi.y2024.day19.part1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandlerTest {
    @Test
    void getWords() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertThat(handler.getWords())
                .hasSize(8)
                .containsExactly("brwrr", "bggr", "gbbr", "rrbgbr", "ubwu", "bwurrg", "brgr", "bbrgwb");
    }

    @Test
    void getBricks() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertThat(handler.getBricks())
                .hasSize(8)
                .containsOnly("r", "wr", "b", "g", "bwu", "rb", "gb", "br");
        assertThat(handler.getBricks().get(0))
                .isEqualTo("bwu");
    }

    @Test
    void processData() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertThat(handler.processData())
                .isEqualTo(6);

    }

    @Test
    void processProblemData() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertThat(handler.processData())
                .isEqualTo(324);

    }

    @Test
    void difficultExampleProcessData() {
        ReadData readData = new ReadData("input00.txt");
        Handler handler = new Handler(readData.getOutput());
        assertThat(handler.processData())
                .isEqualTo(0);
    }
}