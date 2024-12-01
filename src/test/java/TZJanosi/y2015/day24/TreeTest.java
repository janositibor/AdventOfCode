package TZJanosi.y2015.day24;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    void treeTest(){
        Tree tree=new Tree();
        Sleigh sleigh=new Sleigh(Arrays.asList(1,2,3,4,5,6));
//        Sleigh sleigh=new Sleigh(Arrays.asList(1, 3, 5, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113));

        Node node=new Node(null,sleigh);
        tree.process(node);
        System.out.println("Results: "+Tree.getSucceed());

    }

}