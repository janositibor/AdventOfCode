package tzjanosi.y2024.day07;

import java.util.List;

public class EquationService {
    private List<Long> expectedResults;
    private List<List<Integer>> operands;

    public EquationService(List<Long> expectedResults, List<List<Integer>> operands) {
        if(expectedResults.size()!= operands.size()){
            throw new IllegalArgumentException("Different List size!\n"+expectedResults.size() + " vs. " + operands.size());
        }
        this.expectedResults = expectedResults;
        this.operands = operands;
    }

    public long calculate(){
        long result=0;
        for (int i = 0; i < expectedResults.size(); i++) {
            Equation equation=new Equation(expectedResults.get(i),operands.get(i));
            if(equation.isValid()){
                result+=expectedResults.get(i);
            }
        }
        return result;
    }

}
