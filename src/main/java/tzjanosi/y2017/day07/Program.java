package tzjanosi.y2017.day07;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private String name;
    private int weight;
    private List<Program> children = new ArrayList<>();
    private List<String> childrenNames = new ArrayList<>();
    private Program parent;

    public Program(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public void addChildName(String childName) {
        childrenNames.add(childName);
    }

    public void addChild(Program child) {
        child.setParent(this);
        children.add(child);
    }

    public void setParent(Program parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<Program> getChildren() {
        return children;
    }

    public List<String> getChildrenNames() {
        return childrenNames;
    }

    public Program getParent() {
        return parent;
    }
}
