package TZJanosi.y2015.day24.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sleigh {
//    private List<Integer> allPackages=Arrays.asList(1, 3, 5, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113);
    private List<Integer> allPackages;
    private List<Integer> packagesToWorkWith;
    private int weightLimit=0;
    private List<Container> slots= Arrays.asList(new Container(),new Container(),new Container());
    private boolean valid=true;
//    private Sleigh parent;

    public Sleigh(List<Integer> allPackages) {
        this.allPackages = List.copyOf(allPackages);
        packagesToWorkWith= new ArrayList<>(this.allPackages);
        setWeightLimit();
    }
    public Sleigh(Sleigh original){
        weightLimit=original.weightLimit;
        valid= original.valid;
        allPackages=List.copyOf(original.allPackages);
        packagesToWorkWith=new ArrayList<>(original.packagesToWorkWith);
        slots=new ArrayList<>();
        for (int i = 0; i < original.slots.size(); i++) {
            slots.add(new Container(original.slots.get(i)));
        }
    }

    @Override
    public String toString() {
        return "Sleigh{" +
                "packagesToWorkWith=" + packagesToWorkWith +
                ", slots=" + slots +
                '}';
    }

    public Sleigh replaceNextElementIntoASlot(int i){
        if(packagesToWorkWith.size()==0){
            throw new IllegalStateException("No more package to replace!");
        }
        if(i>=slots.size()){
            throw new IllegalArgumentException("There is no slot no: "+i);
        }
        int value= packagesToWorkWith.get(packagesToWorkWith.size()-1);
        packagesToWorkWith.remove(packagesToWorkWith.size()-1);
        slots.get(i).addPackage(value);
        setValid();
        return this;
    }

    public boolean isPacked(){
        return packagesToWorkWith.size()==0;
    }
    public void setValid(){
//        if(packagesToWorkWith.size()==0){
//            valid=true;
//        }
        if(slots.stream().anyMatch(container->container.getWeight()>weightLimit)){
            valid=false;
        }
        if(isPacked()){
            Container footSlot=slots.get(0);
            int numberOfPackagesAtFoot=footSlot.getNumberOfPackages();
            if(slots.stream().anyMatch(container -> container.getNumberOfPackages()<numberOfPackagesAtFoot)){
                valid=false;
            }
        }
    }

    private void setWeightLimit(){
        int totalWeight=allPackages.stream().mapToInt(x->x.intValue()).sum();
        if (totalWeight%3!=0){
            valid=false;
            throw new IllegalArgumentException("No possibility to divide into three equal groups. Total weight is: "+totalWeight);
        }
        weightLimit=totalWeight/3;
    }

//        public Sleigh getParent() {
//        return parent;
//    }
//
//    public void setParent(Sleigh parent) {
//        this.parent = parent;
//    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public boolean isValid() {
        return valid;
    }

    public List<Container> getSlots() {
        return slots;
    }


}
