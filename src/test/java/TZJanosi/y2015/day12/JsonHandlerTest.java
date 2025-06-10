package TZJanosi.y2015.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonHandlerTest {
    private JsonHandler handler = new JsonHandler("");

    @Test
    void getNumbers() {
        handler.setInput("[1,2,3]");
        assertEquals(List.of(1, 2, 3), handler.getNumbers());
        handler.setInput("{\"a\":2,\"b\":4}");
        assertEquals(List.of(2, 4), handler.getNumbers());
        handler.setInput("[[[3]]]");
        assertEquals(List.of(3), handler.getNumbers());
        handler.setInput("{\"a\":{\"b\":4},\"c\":-1}");
        assertEquals(List.of(4, -1), handler.getNumbers());
        handler.setInput("{\"a\":[-1,1]}");
        assertEquals(List.of(-1, 1), handler.getNumbers());
        handler.setInput("[-1,{\"a\":1}]");
        assertEquals(List.of(-1, 1), handler.getNumbers());
        handler.setInput("[]");
        assertEquals(List.of(), handler.getNumbers());
        handler.setInput("{}");
        assertEquals(List.of(), handler.getNumbers());
    }

    @Test
    void getSumOfNumbers() {
        handler.setInput("[1,2,3]");
        assertEquals(6, handler.getSumOfNumbers());
        handler.setInput("{\"a\":2,\"b\":4}");
        assertEquals(6, handler.getSumOfNumbers());
        handler.setInput("[[[3]]]");
        assertEquals(3, handler.getSumOfNumbers());
        handler.setInput("{\"a\":{\"b\":4},\"c\":-1}");
        assertEquals(3, handler.getSumOfNumbers());
        handler.setInput("{\"a\":[-1,1]}");
        assertEquals(0, handler.getSumOfNumbers());
        handler.setInput("[-1,{\"a\":1}]");
        assertEquals(0, handler.getSumOfNumbers());
        handler.setInput("[]");
        assertEquals(0, handler.getSumOfNumbers());
        handler.setInput("{}");
        assertEquals(0, handler.getSumOfNumbers());
    }

    @Test
    void getSumOfNumbersProblemData() {
        ReadData readData = new ReadData("input.txt");
        handler.setInput(readData.getOutput().get(0));
        assertEquals(119433, handler.getSumOfNumbers());
    }

    @Test
    void getNumbersProblemDataPart1() {
        ReadData readData = new ReadData("input.txt");
        handler.setInput(readData.getOutput().get(0));
        assertEquals(119433, handler.getNumbers(t -> false));
    }

    @Test
    void getNumbersProblemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        handler.setInput(readData.getOutput().get(0));
        assertEquals(68466, handler.getNumbers(t -> handler.containsRed(t)));
    }
}