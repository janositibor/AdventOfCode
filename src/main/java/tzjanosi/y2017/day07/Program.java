package tzjanosi.y2017.day07;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private String name;
    private int weight;
    private List<Program> children = new ArrayList<>();
    private List<String> childrenNames = new ArrayList<>();
    private Program parent;
    private boolean totalWeightOk;
    private int totalWeight;

    public Program(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Program(String name, int weight, boolean totalWeightOk) {
        this.name = name;
        this.weight = weight;
        this.totalWeightOk = totalWeightOk;
        if (totalWeightOk) {
            totalWeight = weight;
        }
    }

    public int calculateTotalWeight() {
        if (!allChildrenAreReady()) {
            return 0;
        }
        if (allChildrenWeightAreEqual()) {
            totalWeight = weight + children.stream().mapToInt(Program::getTotalWeight).sum();
            totalWeightOk = true;
            return 0;
        }
        return calculateBalancedWeight();
    }

    private int calculateBalancedWeight() {
        int right;
        int wrong;
        for (int i = 1; i < children.size(); i++) {
            if (children.get(i - 1).getTotalWeight() != children.get(i).getTotalWeight()) {
                if (i >= 2) {
                    right = i - 1;
                    wrong = i;
                } else {
                    if (children.get(0).totalWeight == children.get(2).totalWeight) {
                        right = 0;
                        wrong = 1;
                    } else {
                        right = 1;
                        wrong = 0;
                    }
                }
                int difference = children.get(right).totalWeight - children.get(wrong).totalWeight;
                return children.get(wrong).weight + difference;
            }
        }
        throw new IllegalStateException("No difference found ...");
    }

    private boolean allChildrenWeightAreEqual() {
        for (int i = 1; i < children.size(); i++) {
            if (children.get(i - 1).getTotalWeight() != children.get(i).getTotalWeight()) {
                return false;
            }
        }
        return true;
    }

    private boolean allChildrenAreReady() {
        return children.stream().allMatch(p -> p.totalWeightOk);
    }


//    public int totalWeight(){
//        if(children.isEmpty()){
//            return weight;
//        }
//        if(allChildrenHasTotalWeight()){
//            return weight
//        }
//    }

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

    public boolean isTotalWeightOk() {
        return totalWeightOk;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
