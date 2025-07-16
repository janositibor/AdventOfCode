package tzjanosi.y2015.day24.part1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTest {
    @Test
    void treeWithTestData() {
        Tree tree = new Tree();
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));
        tree.process(sleigh);
        tree.orderSucceedList();
        assertEquals(99, tree.getOrderedSucceed().get(0).getSlots().get(0).getQe());
    }

    @Test
    void treeTest() {
        Tree tree = new Tree();
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 3, 5, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113));
        tree.process(sleigh);
        tree.orderSucceedList();
        assertEquals(11266889531L, tree.getOrderedSucceed().get(0).getSlots().get(0).getQe());
    }

}