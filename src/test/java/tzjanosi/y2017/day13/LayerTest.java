package tzjanosi.y2017.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LayerTest {
    @Test
    void positionAfterTimeTest() {
        Layer layer = new Layer(0, 1);
        assertEquals(0, layer.positionAfterTime(0));
        assertEquals(0, layer.positionAfterTime(1));
        assertEquals(0, layer.positionAfterTime(2));
        assertEquals(0, layer.positionAfterTime(3));
        assertEquals(0, layer.positionAfterTime(4));
        assertEquals(0, layer.positionAfterTime(5));
        assertEquals(0, layer.positionAfterTime(6));
        assertEquals(0, layer.positionAfterTime(7));
        layer = new Layer(0, 2);
        assertEquals(0, layer.positionAfterTime(0));
        assertEquals(1, layer.positionAfterTime(1));
        assertEquals(0, layer.positionAfterTime(2));
        assertEquals(1, layer.positionAfterTime(3));
        assertEquals(0, layer.positionAfterTime(4));
        assertEquals(1, layer.positionAfterTime(5));
        assertEquals(0, layer.positionAfterTime(6));
        assertEquals(1, layer.positionAfterTime(7));
        layer = new Layer(0, 3);
        assertEquals(0, layer.positionAfterTime(0));
        assertEquals(1, layer.positionAfterTime(1));
        assertEquals(2, layer.positionAfterTime(2));
        assertEquals(1, layer.positionAfterTime(3));
        assertEquals(0, layer.positionAfterTime(4));
        assertEquals(1, layer.positionAfterTime(5));
        assertEquals(2, layer.positionAfterTime(6));
        assertEquals(1, layer.positionAfterTime(7));
        layer = new Layer(0, 4);
        assertEquals(0, layer.positionAfterTime(0));
        assertEquals(1, layer.positionAfterTime(1));
        assertEquals(2, layer.positionAfterTime(2));
        assertEquals(3, layer.positionAfterTime(3));
        assertEquals(2, layer.positionAfterTime(4));
        assertEquals(1, layer.positionAfterTime(5));
        assertEquals(0, layer.positionAfterTime(6));
        assertEquals(1, layer.positionAfterTime(7));
        layer = new Layer(0, 5);
        assertEquals(0, layer.positionAfterTime(0));
        assertEquals(1, layer.positionAfterTime(1));
        assertEquals(2, layer.positionAfterTime(2));
        assertEquals(3, layer.positionAfterTime(3));
        assertEquals(4, layer.positionAfterTime(4));
        assertEquals(3, layer.positionAfterTime(5));
        assertEquals(2, layer.positionAfterTime(6));
        assertEquals(1, layer.positionAfterTime(7));
        assertEquals(0, layer.positionAfterTime(8));

    }

}