package TZJanosi.y2024.day11;

import java.util.*;

public class StoneService {
    private Set<Stone> stones=new HashSet<>();
    private String[] numbers;
    private int stepNumber;
    private Map<Long, Map<Integer,Long>> memory=new HashMap<>();
    private long totalNumberOfStones;

    public StoneService(int stepNumber, String input) {
        this.stepNumber=stepNumber;
        Scanner scanner=new Scanner(input);
        while(scanner.hasNextInt()){
            long value= scanner.nextInt();
            stones.add(new Stone(value));
        }
    }

    public void advancedCalculation(){
        long result=0;
        for (Stone stone:stones) {
            result+=advancedCalculationNode(1,stone.getValueAsString());
        }
        totalNumberOfStones=result;
    }

    private long advancedCalculationNode(int actualStep, String value) {
        long longValue=Long.parseLong(value);
        if(actualStep==stepNumber){
            return leafCounter(value);
        }
        Map<Integer,Long> level1=memory.get(longValue);
        Long foundInMemory=null;
        if(level1!=null){
            foundInMemory= level1.get(actualStep);
            if(foundInMemory!=null){
                return foundInMemory;
            }
        }

        long result=0;

        for (String child: next(value)) {
            result+=advancedCalculationNode(actualStep+1,child);
        }
        if(level1==null){
            memory.put(longValue,new HashMap<>(Map.of(actualStep,result)));
        } else if(foundInMemory==null){
            level1.put(actualStep,result);
        }
        return result;
    }

    private long leafCounter(String value) {
        int length=value.length();
        if(length>1){
            value=removeLeadingZeros(value);
        }
        length=value.length();
        if(length%2==0){
            return 2L;
        }
        return 1L;
    }


    private String[] next(String value) {
        String[] result;
        int length=value.length();
        if(length>1){
            value=removeLeadingZeros(value);
        }
        length=value.length();
        if(value.equals("0")){
            result= new String[] {"1"};
            return result;
        }
        if(length%2==0){
            int halfLength=length/2;
            result=new String[]{value.substring(0,halfLength),value.substring(halfLength)};
            return result;
        }
        result= new String[]{Long.toString(Long.parseLong(value)*2024)};
        return result;
    }

    private String removeLeadingZeros(String value) {
        int startFrom = 0;
        int length = value.length();
        while (startFrom < length && value.charAt(startFrom) == '0') {
            startFrom++;
        }
        if (startFrom == 0) {
            return value;
        }
        if (startFrom == length) {
            return "0";
        } else {
            return value.substring(startFrom);
        }
    }

    private void step(){
        Set<Stone> temp=new HashSet<>();
        for (Stone stone:stones) {
            temp.addAll(stone.next());
        }
        stones=temp;
    }

    public void calculation(){
        for (int i = 0; i < stepNumber; i++) {
            step();
//            System.out.println(stones);
        }
    }

    public int getNumberOfStones(){
        return stones.size();
    }


    public long getTotalNumberOfStones() {
        return totalNumberOfStones;
    }

    public Set<Stone> getStones() {
        return stones;
    }
}
