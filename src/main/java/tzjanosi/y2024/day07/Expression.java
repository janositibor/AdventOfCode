package tzjanosi.y2024.day07;

import java.util.*;

public class Expression {
    private long expectedResult;
    private boolean valid;
    private Deque<Long> operands=new ArrayDeque<>();

    public Expression(long expectedResult, List<Integer> operands) {
        this.expectedResult = expectedResult;
        setOperands(operands);
//        init();
    }




    private void setOperands(List<Integer> operands) {
        int numberOfOperands=operands.size();
        for (int i = 1; i <= numberOfOperands; i++) {
            this.operands.push(Long.valueOf(operands.get(numberOfOperands-i)));
        }


    }
    public void startCalculation(){
        Long actual=operands.pop();
        calculate(actual,"+");
        if (!valid) {
            calculate(actual, "*");
        }
        if (!valid) {
            calculate(actual, "||");
        }
    }

    public Long calculate(Long previousOperands, String operator){

        Long result;

        Long actual=operands.pop();
        if (operands.isEmpty()) {
            result=function(previousOperands,actual,operator);
            if(result==expectedResult){
                valid = true;
            }
            operands.push(actual);
            return result;
        }
        if (!valid) {
            calculate(function(previousOperands, actual, operator), "+");
        }
        if (!valid) {
            calculate(function(previousOperands,actual,operator),"*");
        }
        if (!valid) {
            calculate(function(previousOperands, actual, operator), "||");
        }
        operands.push(actual);
        return 0L;
    }

    private Long function(Long previousOperands, Long actual, String operator) {
        Long result;
        switch(operator){
            case("+"):
                result=previousOperands+actual;
                break;
            case("*"):
                result=previousOperands*actual;
                break;
            case("||"):
                result=concatenateNumbers(previousOperands,actual);
                break;
            default:
                result = 0L;
        }
        return result;
    }
    private Long concatenateNumbers(Long integer1, Long integer2) {
        return Long.valueOf(Long.toString(integer1).concat(Long.toString(integer2)));
    }

    public boolean isValid() {
        return valid;
    }
}
