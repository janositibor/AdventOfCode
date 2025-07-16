package tzjanosi_temp.y2024.day05;

import java.util.ArrayList;
import java.util.List;

public class Printer {
    private List<Integer[]> restrictions;
    private List<List<Integer>> pageOrders;
    private List<List<Integer>> validPageOrders=new ArrayList<>();
    private int sumOfCentralElements;
    private List<List<Integer>> fixedPageOrders=new ArrayList<>();
    private int sumOfFixedCentralElements;

    public Printer(List<Integer[]> restrictions, List<List<Integer>> pageOrders) {
        this.restrictions = restrictions;
        this.pageOrders = ListInList.deepClone(pageOrders);
        setValidPageOrders();
        sumOfCentralElements = calculateSumOfCentralElements(validPageOrders);
        sumOfFixedCentralElements = calculateSumOfCentralElements(fixedPageOrders);


    }

    private int calculateSumOfCentralElements(List<List<Integer>> inputPageOrders) {
        int result=0;
        for (List<Integer> pageOrder:inputPageOrders) {
            result+=pageOrder.get((pageOrder.size()-1)/2);
        }
        return result;
    }
    private void setValidPageOrders(){
        for (int i = 0; i < pageOrders.size(); i++) {
            List<Integer> pageOrder=pageOrders.get(i);
            if (calculateValidPageOrder(pageOrder) < 0) {
                validPageOrders.add(pageOrder);
            }
            else{
                fixPageOrder(i);
            }
        }
    }

    private void fixPageOrder(int index) {
        int indexOfRestriction;
        int temp;
        int i;
        int j;
        List<Integer> fixedPageOrder = new ArrayList<>(pageOrders.get(index));
        while ((indexOfRestriction = calculateValidPageOrder(fixedPageOrder)) >= 0) {
            Integer[] restrict= restrictions.get(indexOfRestriction);
            i = fixedPageOrder.indexOf(restrict[0]);
            j = fixedPageOrder.indexOf(restrict[1]);

            temp=fixedPageOrder.get(i);
            fixedPageOrder.set(i,fixedPageOrder.get(j));
            fixedPageOrder.set(j,temp);
        }
        if(fixedPageOrder.size()%2==0){
            throw new IllegalStateException("Even: "+fixedPageOrder.size()+fixedPageOrder.toString());
        }
        fixedPageOrders.add(fixedPageOrder);
    }

    private int calculateValidPageOrder(List<Integer> pageOrder) {
        for (int i = 0; i < restrictions.size(); i++) {
            Integer[] restrict= restrictions.get(i);
            if(!fulfilCriterion(pageOrder,restrict)){
                return i;
            }
        }
        return -1;
    }

    private boolean fulfilCriterion(List<Integer> pageOrder, Integer[] restrict) {
        int i;
        int j;

        if((i = pageOrder.indexOf(restrict[0]))<0 || (j= pageOrder.indexOf(restrict[1]))<0){
            return true;
        }
        return i<j;
    }

    public List<Integer[]> getRestrictions() {
        return restrictions;
    }

    public List<List<Integer>> getPageOrders() {
        return pageOrders;
    }

    public List<List<Integer>> getValidPageOrders() {
        return validPageOrders;
    }

    public int getSumOfCentralElements() {
        return sumOfCentralElements;
    }

    public List<List<Integer>> getFixedPageOrders() {
        return fixedPageOrders;
    }

    public int getSumOfFixedCentralElements() {
        return sumOfFixedCentralElements;
    }
}
