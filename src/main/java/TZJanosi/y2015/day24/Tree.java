package TZJanosi.y2015.day24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Tree {
    private static List<Sleigh> succeed=new ArrayList<>();
    private static List<Sleigh> orderedSucceed=new ArrayList<>();
    private static int shortest=Integer.MAX_VALUE;
    private static long bestQE=Long.MAX_VALUE;

    private boolean interestingSleigh(Sleigh sleigh){
        Container footContainer=sleigh.getSlots().get(0);
        if(footContainer.getNumberOfPackages()>shortest){
            return false;
        }
        if(footContainer.getQe()>=bestQE){
            return false;
        }
        return true;
    }
    private void addSleighToSucced(Sleigh sleigh){
        Tree.succeed.add(new Sleigh(sleigh));
        Container footContainer=sleigh.getSlots().get(0);
        if(footContainer.getNumberOfPackages()<shortest){
            shortest=footContainer.getNumberOfPackages();
        }
        if(footContainer.getQe()<bestQE){
            bestQE=footContainer.getQe();
        }
    }

    public void process(Sleigh sleigh){
        if(sleigh.isValid() && !sleigh.isPacked()){
            replaceNextElement(sleigh);
        }
        if(sleigh.isValid() && sleigh.isPacked()){
            addSleighToSucced(sleigh);
        }
    }
    private void replaceNextElement(Sleigh sleigh){
        Sleigh child;
        for (int i = 0; i < sleigh.getSlots().size(); i++) {
            if(sleigh.isValid() && interestingSleigh(sleigh)) {
                child = new Sleigh(sleigh);
                child.replaceNextElementIntoASlot(i);
                process(child);
            }
        }
    }

    public static void orderSucceedList(){
        orderedSucceed=succeed.stream().sorted(Comparator
                .comparing((Sleigh s)->s.getSlots().get(0).getNumberOfPackages())
                .thenComparing((Sleigh s)->s.getSlots().get(0).getQe())
                )
                .toList();
    }

    public static List<Sleigh> getSucceed() {
        return succeed;
    }

    public static List<Sleigh> getOrderedSucceed() {
        return orderedSucceed;
    }
}
