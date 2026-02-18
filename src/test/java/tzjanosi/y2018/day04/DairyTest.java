package tzjanosi.y2018.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DairyTest {
    @Test
    void initTest() {
        ReadData readData = new ReadData("testInput.txt");
        Dairy diary = new Dairy(readData.getOutput());
        assertEquals(10, diary.sleepiestGuard());
    }

    @Test
    void bestChanceToSneakTest() {
        ReadData readData = new ReadData("testInput.txt");
        Dairy diary = new Dairy(readData.getOutput());
        assertEquals(10, diary.sleepiestGuard());
        assertEquals(240, diary.bestChanceToSneak());
    }

    @Test
    void sortTest() {
        ReadData readData = new ReadData("input.txt");
        Dairy diary = new Dairy(readData.getOutput());
        assertEquals(87681, diary.bestChanceToSneak());
    }

}