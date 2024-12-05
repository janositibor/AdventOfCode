package TZJanosi.y2024.day05;

import java.util.ArrayList;
import java.util.List;

public class Printer {
    private List<Integer[]> restrictions;
    private List<List<Integer>> pageOrders;
    private List<List<Integer>> validPageOrders=new ArrayList<>();
    private int sumOfCentralElements;

    public Printer(List<Integer[]> restrictions, List<List<Integer>> pageOrders) {
        this.restrictions = restrictions;
        this.pageOrders = pageOrders;
        setValidPageOrders();
        setSumOfCentralElements();
    }

    private void setSumOfCentralElements(){
        int result=0;
        for (List<Integer> pageOrder:validPageOrders) {
            result+=pageOrder.get((pageOrder.size()-1)/2);
        }
        sumOfCentralElements=result;
    }
    private void setValidPageOrders(){
        for (List<Integer> pageOrder:pageOrders) {
            if(isValidPageOrder(pageOrder)){
                validPageOrders.add(pageOrder);
            }
        }
    }

    private boolean isValidPageOrder(List<Integer> pageOrder) {
        for (Integer[] restrict:restrictions) {
            if(!fulfilCriterion(pageOrder,restrict)){
                return false;
            }
        }
        return true;
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
}
