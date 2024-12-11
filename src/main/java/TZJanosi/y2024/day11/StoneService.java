package TZJanosi.y2024.day11;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StoneService {
    private Set<Stone> stones=new HashSet<>();
    private int stepNumber;

    public StoneService(int stepNumber, String input) {
        this.stepNumber=stepNumber;
        Scanner scanner=new Scanner(input);
        while(scanner.hasNextInt()){
            long value= scanner.nextInt();
            stones.add(new Stone(value));
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

    public Set<Stone> getStones() {
        return stones;
    }
}
