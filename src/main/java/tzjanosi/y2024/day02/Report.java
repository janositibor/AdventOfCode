package tzjanosi.y2024.day02;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Integer> values;

    public Report(List<Integer> values) {
        this.values = values;
    }

    private boolean isIncreasing(){
        for (int i = 1; i < values.size(); i++) {
            if(values.get(i)-values.get(i-1)<=0){
                return false;
            }
        }
        return true;
    }
    private boolean isDecreasing(){
        for (int i = 1; i < values.size(); i++) {
            if(values.get(i)-values.get(i-1)>=0){
                return false;
            }
        }
        return true;
    }
    private boolean isCorrectStepSize(){
        int stepSize;
        for (int i = 1; i < values.size(); i++) {
            stepSize=Math.abs(values.get(i)-values.get(i-1));
            if(stepSize<1 || stepSize>3){
                return false;
            }
        }
        return true;
    }

    public boolean isSafe(){
        return((isCorrectStepSize() && (isIncreasing() || isDecreasing())));
    }

    public boolean isSafeWithDampener(){
        return((isCorrectStepSize() && (isIncreasing() || isDecreasing())) || isDampenerSafe());
    }

    public boolean isDampenerSafe(){
        int i=0;
        Report report;
        List<Integer> listToWorkWith;
        while(i < values.size()){
            listToWorkWith=new ArrayList<>(values);
            listToWorkWith.remove(i);
            report =new Report(new ArrayList<>(listToWorkWith));
            if(report.isSafe()){
                return true;
            }
            i++;
        }
        return false;
    }

}
