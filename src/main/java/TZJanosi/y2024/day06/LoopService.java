package TZJanosi.y2024.day06;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoopService {
    private GuardPath guardPath;
    private int numberOfLoops=0;
    private List<Trio> coordinatesForLoop=new ArrayList<>();
    private GuardPath guardPathToCheck;

    public LoopService(char[][] matrixMap) {
        guardPath=new GuardPath(matrixMap);
        guardPath.findPath(false);
        System.out.println(guardPath.getPath().getPath().size());
    }

    public void countLoops(){
        Integer[] actualCoordinates=new Integer[2];
        List<Trio> coordinatesForLoop=new ArrayList<>();
//        Trio actual;
        for (int i = 1; i <guardPath.getPath().getPath().size() ; i++) {
            System.out.println(i +" > "+guardPath.getPath().getPath().size());
            actualCoordinates[0]=guardPath.getPath().getPath().get(i).getValue()[0];
            actualCoordinates[1]=guardPath.getPath().getPath().get(i).getValue()[1];
//            System.out.println("Check: "+Arrays.toString(actualCoordinates));
            guardPathToCheck=new GuardPath(guardPath.getOriginalMatrixMap());
            guardPathToCheck.addClog(actualCoordinates);
            boolean check=(actualCoordinates[0]==6 && actualCoordinates[1]==3);
            if(check){
//                System.out.println(Arrays.deepToString(guardPathToCheck.getOriginalMatrixMap()));
            }
            if(guardPathToCheck.findPath(check)){
//                System.out.println("Succed: "+Arrays.toString(actualCoordinates));
                Trio actual=new Trio(actualCoordinates);
                if(!coordinatesForLoop.contains(actual)) {
                    numberOfLoops++;
                    coordinatesForLoop.add(new Trio(actualCoordinates));
                }
            }
        }
    }

    public int getNumberOfLoops() {
        return numberOfLoops;
    }
}
