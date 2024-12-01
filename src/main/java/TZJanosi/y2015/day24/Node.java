package TZJanosi.y2015.day24;

import java.util.List;

public class Node {
    private Node parent;
    private Sleigh sleigh;

    public Node(Node parent, Sleigh sleigh) {
        this.parent = parent;
        this.sleigh = new Sleigh(sleigh);
    }

    public Sleigh getSleigh() {
        return sleigh;
    }

    public boolean isValid() {
        return sleigh.isValid();
    }

    public boolean isPacked() {
        return sleigh.isPacked();
    }

//    public Node(Node original) {
//        this.parent = new Node(parent);
//        this.sleigh = new Sleigh(sleigh);
//    }

}
