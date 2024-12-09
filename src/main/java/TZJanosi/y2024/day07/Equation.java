package TZJanosi.y2024.day07;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Equation {
    private long expectedResult;
    private List<Integer> operands;
    private List<Integer> operators=new ArrayList<>();
    private Map<String,Long> extremities;

    public Equation(long expectedResult, List<Integer> operands) {
        this.expectedResult = expectedResult;
        this.operands = operands;
        init();
    }

    public boolean isValid(){
        if(expectedResult==extremities.get("min") || expectedResult==extremities.get("max")){
            return true;
        }
        int limitNumber=(int) Math.pow(2,operators.size());
        for (int i = 0; i < limitNumber; i++) {
            adjustOperators(i);
            if(expectedResult==calculateResult()){
                return true;
            }
        }
        return false;
    }

    private long calculateResult(){
        long result=operands.get(0);
        for (int i = 0; i < operators.size(); i++) {
            if(operators.get(i)==1){
                result*=operands.get(i+1);
            }
            else{
                result+=operands.get(i+1);
            }
            if(result<0){
                System.out.println("Result should be positive: "+result);
            }
        }
        return result;
    }

    private void init(){
        setExtremities();
        adjustOperators(0);
    }
    private void adjustOperators(int index){
        operators=new ArrayList<>(operands.size()-1);
        for (int i = 0; i < operands.size()-1; i++) {
            operators.add(0);
        }
        char[] bin=Integer.toString(index,2).toCharArray();
        int shift=operators.size()-bin.length;
        if(shift<0){
            throw new IllegalStateException("Too long input number for operands: "+index);
        }
        for (int i = 0; i < bin.length; i++) {
            if(bin[i]=='1') {
                operators.set(i + shift, 1);
            }
        }
    }

    private void setExtremities(){
        extremities=Map.ofEntries(
                Map.entry("min",calculateMin()),
                Map.entry("max",calculateMax()));
    }

    private Long calculateMin(){
        return operands.stream().reduce(0,(n,m)->n+m).longValue();
    }

    private Long calculateMax(){
        return operands.stream().reduce(1,(n,m)->n*m).longValue();
    }

    public void setOperators(List<Integer> operators) {
        this.operators = operators;
    }
}
