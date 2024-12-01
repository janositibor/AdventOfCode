package TZJanosi.y2015.day24;

import java.util.HashSet;
import java.util.Set;

public class Tree {
    private static Set<Sleigh> succeed=new HashSet<>();

    public void process(Node node){
        if(node.isValid() && !node.isPacked()){
            replaceNextElement(node);
        }
        if(node.isValid() && node.isPacked()){
            Tree.succeed.add(new Sleigh(node.getSleigh()));
        }
    }
    private void replaceNextElement(Node node){
        for (int i = 0; i < node.getSleigh().getSlots().size(); i++) {
            if(node.isValid()) {
                Node child = new Node(node, node.getSleigh());
                child.getSleigh().replaceNextElementIntoASlot(i);
                process(child);
            }
        }
    }

    public static Set<Sleigh> getSucceed() {
        return succeed;
    }
}
