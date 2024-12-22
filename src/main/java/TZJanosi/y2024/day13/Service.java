package TZJanosi.y2024.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {
    private List<Machine> machines=new ArrayList<>();

    public Service(List<String> input,long shiftTarget){
        int length= input.size();
        if(length%3>0){
            throw new IllegalArgumentException("The length of input is not correct: "+input.size());
        }
        int numberOfMachines=length/3;
        for (int i = 0; i < numberOfMachines; i++) {
            int index=3*i;
            Map<String,Integer> aShifts = getShiftsFromLine(input.get(index+0));
            Map<String,Integer> bShifts = getShiftsFromLine(input.get(index+1));
            Map<String,Integer> target = getTargetFromLine(input.get(index+2));
            Machine machine = new Machine(aShifts.get("x"),aShifts.get("y"),bShifts.get("x"),bShifts.get("y"),target.get("x"),target.get("y"),shiftTarget);
            machines.add(machine);
        }
    }

    public long getTotalCost(){
        return machines.stream().filter(m->m.isIspossible()).mapToLong(m->m.getCost()).sum();
    }

    public void calculate(){
        machines.stream().forEach(m->m.calculate());
    }

    private Map<String, Integer> getTargetFromLine(String s) {
        int xFrom=s.indexOf("X=")+2;
        int xTo=s.indexOf(",",xFrom);
        int yFrom=s.indexOf("Y=",xTo)+2;
        Map<String, Integer> output=Map.ofEntries(
                Map.entry("x",Integer.valueOf(s.substring(xFrom,xTo))),
                Map.entry("y",Integer.valueOf(s.substring(yFrom)))
        );
        return output;
    }

    private Map<String, Integer> getShiftsFromLine(String s) {
        int xFrom=s.indexOf("+")+1;
        int xTo=s.indexOf(",",xFrom);
        int yFrom=s.indexOf("+",xTo)+1;
        Map<String, Integer> output=Map.ofEntries(
                Map.entry("x",Integer.valueOf(s.substring(xFrom,xTo))),
                Map.entry("y",Integer.valueOf(s.substring(yFrom)))
        );
        return output;
    }

    public List<Machine> getMachines() {
        return machines;
    }
}
