package TZJanosi.y2024.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerPart2Test {
    @Test
    void sampleDataTest() {
        HandlerPart2 handlerPart2 = new HandlerPart2("testInput.txt", 6, 12);
        assertEquals("6,1", handlerPart2.getFirstBlockingBrick());
    }

    @Test
    void problemDataTest() {
        HandlerPart2 handlerPart2 = new HandlerPart2("input2.txt", 70, 1024);
        assertEquals("60,21", handlerPart2.getFirstBlockingBrick());
    }

}