package tzjanosi_temp.y2015.day19;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProducerTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        Producer producer = new Producer(readData.getOutput());
        assertTrue(producer.getCreatedMolecules().isEmpty());
        assertEquals("HOH", producer.getBasicMolecule());
        assertThat(producer.getReplacements())
                .hasSize(2)
                .containsOnly(Map.entry("H", List.of("HO", "OH")),
                        Map.entry("O", List.of("HH")));
    }

    @Test
    void findNumberOfNewMoleculesTestData() {
        ReadData readData = new ReadData("testInput.txt");
        Producer producer = new Producer(readData.getOutput());
        assertEquals(4, producer.findNumberOfNewMolecules());
        assertThat(producer.getCreatedMolecules())
                .hasSize(4)
                .containsOnly("HOOH",
                        "HOHO",
                        "OHOH",
                        "HHHH");

    }

    @Test
    void findNumberOfNewMoleculesProblemData() {
        ReadData readData = new ReadData("input.txt");
        Producer producer = new Producer(readData.getOutput());
        assertEquals(509, producer.findNumberOfNewMolecules());
    }

}