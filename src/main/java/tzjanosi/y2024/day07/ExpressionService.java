package tzjanosi.y2024.day07;

import java.util.List;

public class ExpressionService {
    private List<Long> expectedResults;
    private List<List<Integer>> operands;

    public ExpressionService(List<Long> expectedResults, List<List<Integer>> operands) {
        if(expectedResults.size()!= operands.size()){
            throw new IllegalArgumentException("Different List size!\n"+expectedResults.size() + " vs. " + operands.size());
        }
        this.expectedResults = expectedResults;
        this.operands = operands;
    }

    public long calculate(){
        long result=0;
        for (int i = 0; i < expectedResults.size(); i++) {
            Expression expression=new Expression(expectedResults.get(i),operands.get(i));
            expression.startCalculation();
            if(expression.isValid()){
                result+=expectedResults.get(i);
            }
        }
        return result;
    }
}
