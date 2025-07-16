package tzjanosi_temp.y2024.day01;

import java.util.*;

public class TotalDistance {
    private List<Integer> list1;
    private List<Integer> list2;
    private List<Integer> ordered1;
    private List<Integer> ordered2;

    private List<Integer> multiplicator=new ArrayList<>();
    public TotalDistance(List<Integer> list1, List<Integer> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    public int getSimilarityScore(){
        createMultiplicator();
        int result=0;
        for (int i = 0; i < ordered1.size(); i++) {
            result+=ordered1.get(i)* multiplicator.get(i);
        }
        return result;
    }

    public void createMultiplicator(){
        int value;
        int multiplicatorValue;
        int index;
        orderAll();
        List<Integer> listToCheck=new ArrayList<>(ordered2);


        for (int i = 0; i < ordered1.size(); i++) {
            multiplicatorValue=0;
            value=ordered1.get(i);
            index=Collections.binarySearch(listToCheck,value);
            while(index>=0){
                multiplicatorValue++;
                listToCheck.remove(index);
                index=Collections.binarySearch(listToCheck,value);
            }
            multiplicator.add(multiplicatorValue);
        }
    }

    private void orderAll(){
        ordered1=sort(list1);
        ordered2=sort(list2);
    }

    public List<Integer> sort(List<Integer> list){
        return list.stream().sorted(Comparator.naturalOrder()).toList();
    }

    public int getTotalDistance(){
        if(list1.size() != list2.size()){
            throw new IllegalArgumentException("The length of the two lists are not identical: " + list1.size() + " vs. " + list2.size());
        }
        orderAll();
        int result=0;

        for (int i = 0; i < ordered1.size(); i++) {
            result+=Math.abs(ordered1.get(i)-ordered2.get(i));
        }
        return result;
    }

    public List<Integer> getMultiplicator() {
        return multiplicator;
    }
}
