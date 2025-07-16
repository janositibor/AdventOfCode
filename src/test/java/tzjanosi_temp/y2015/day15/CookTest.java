package tzjanosi_temp.y2015.day15;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CookTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        Cook cook = new Cook(readData.getOutput());
        assertThat(cook.getIngredients())
                .hasSize(2)
                .containsExactly(new Ingredient("Butterscotch", -1, -2, 6, 3, 8),
                        new Ingredient("Cinnamon", 2, 3, -2, -1, 3));
    }

    @Test
    void testDataPart1() {
        ReadData readData = new ReadData("testInput.txt");
        Cook cook = new Cook(readData.getOutput());
        assertEquals(62842880, cook.findMaxScorePart1());
    }

    @Test
    void problemDataPart1() {
        ReadData readData = new ReadData("input.txt");
        Cook cook = new Cook(readData.getOutput());
        assertEquals(18965440, cook.findMaxScorePart1());
    }

    @Test
    void testDataPart2() {
        ReadData readData = new ReadData("testInput.txt");
        Cook cook = new Cook(readData.getOutput());
        assertEquals(57600000, cook.findMaxScorePart2());
    }

    @Test
    void problemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        Cook cook = new Cook(readData.getOutput());
        assertEquals(15862900, cook.findMaxScorePart2());
    }

}