package TZJanosi.y2015.day24.part2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tree {
    private List<Sleigh> succeed = new ArrayList<>();
    private List<Sleigh> orderedSucceed = new ArrayList<>();
    private int shortest = Integer.MAX_VALUE;
    private long bestQE = Long.MAX_VALUE;
//    private  long bestQE=77387712;

    private boolean interestingSleigh(Sleigh sleigh) {
        Container footContainer = sleigh.getSlots().get(0);
        if (footContainer.getNumberOfPackages() > shortest) {
            return false;
        }
        if (footContainer.getQe() >= bestQE) {
            return false;
        }
        return true;
    }

    private void addSleighToSucced(Sleigh sleigh) {
        succeed.add(new Sleigh(sleigh));
        Container footContainer = sleigh.getSlots().get(0);
        if (footContainer.getNumberOfPackages() < shortest) {
            shortest = footContainer.getNumberOfPackages();
            System.out.println("shortest: " + shortest);
        }
        if (footContainer.getQe() < bestQE) {
            bestQE = footContainer.getQe();
            System.out.println("bestQE: " + bestQE);
        }
    }

    public void process(Sleigh sleigh) {
        if (sleigh.isValid() && !sleigh.isPacked()) {
            replaceNextElement(sleigh);
        }
        if (sleigh.isPacked()) {
            System.out.println(sleigh);
            if (sleigh.isValid()) {
                addSleighToSucced(sleigh);
            }
        }
    }

    private void replaceNextElement(Sleigh sleigh) {
        Sleigh child;
        for (int i = 0; i < sleigh.getSlots().size(); i++) {
            if (sleigh.isValid() && interestingSleigh(sleigh)) {
                child = new Sleigh(sleigh);
                child.replaceNextElementIntoASlot(i, shortest, bestQE);
                process(child);
            }
        }
    }

    public void orderSucceedList() {
        orderedSucceed = succeed.stream().sorted(Comparator
                        .comparing((Sleigh s) -> s.getSlots().get(0).getNumberOfPackages())
                        .thenComparing((Sleigh s) -> s.getSlots().get(0).getQe())
                )
                .toList();
    }

    public List<Sleigh> getSucceed() {
        return succeed;
    }

    public List<Sleigh> getOrderedSucceed() {
        return orderedSucceed;
    }
}
