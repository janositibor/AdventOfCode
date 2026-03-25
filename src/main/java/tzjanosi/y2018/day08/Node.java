package tzjanosi.y2018.day08;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children = new ArrayList<>();
    private List<Integer> metaData = new ArrayList<>();

    public int sumOfMetaData() {
        int own = metaData.stream().mapToInt(Integer::intValue).sum();
        int fromChildren = children.stream().mapToInt(Node::sumOfMetaData).sum();
        return own + fromChildren;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void addMeta(int meta) {
        metaData.add(meta);
    }
}
