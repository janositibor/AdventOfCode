package TZJanosi.y2024.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HikeForPathSearch {
    private List<List<Integer>> originalMap;
    private List<List<Integer>> resultMap=new ArrayList<>();
    private List<Coordinate> shifts=List.of(new Coordinate(0,-1),new Coordinate(-1,0),new Coordinate(0,1),new Coordinate(1,0));
    private Map<String,Integer> params=Map.ofEntries(Map.entry("min",0),Map.entry("max",9));
    private Coordinate limit=new Coordinate(0,0);

    public HikeForPathSearch(List<List<Integer>> originalMap) {
        this.originalMap = originalMap;
        init();
    }

    private void init() {
        int lengthOfOuterlist= originalMap.size();
        int lengthOfInnerlist= originalMap.get(0).size();
        for (int i = 0; i < lengthOfOuterlist; i++) {
            List<Integer> innerList = new ArrayList<>(Collections.nCopies(lengthOfInnerlist, 0));
            resultMap.add(innerList);
        }
        limit.setX(lengthOfInnerlist-1);
        limit.setY(lengthOfOuterlist-1);
    }
    public void NumberOfPathsFrom(int from){
        for (int i = 0; i < originalMap.size(); i++) {
            for (int j = 0; j < originalMap.get(0).size(); j++) {
                Coordinate coordinate=new Coordinate(j,i);
                if(getValue(coordinate,originalMap)==from){
//                    System.out.println(coordinate);
                    setValue(coordinate,numberOfNeighbourPaths(coordinate,from+1));
                }
            }
        }
    }

    public int calculatePaths(){
        for (int i = params.get("max"); i >= params.get("min") ; i--) {
            NumberOfPathsFrom(i);
            System.out.println(resultMap);
        }
        return countPaths();
    }

    private int countPaths() {
        int result=0;
        for (int i = 0; i < originalMap.size(); i++) {
            for (int j = 0; j < originalMap.get(0).size(); j++) {
                Coordinate coordinate=new Coordinate(j,i);
                if(getValue(coordinate,originalMap)==params.get("min")){
                    result+=getValue(coordinate,resultMap);
                }
            }
        }
        return result;
    }

    private int numberOfNeighbourPaths(Coordinate origo, int seekFor){
        if(seekFor==params.get("max")+1){
            return 1;
        }
        int result=0;
        for (Coordinate shift:shifts) {
            Coordinate coordinate=origo.add(shift);

            if(coordinate.isInsideArea(limit) && getValue(coordinate,originalMap)==seekFor){
                result+=getValue(origo.add(shift),resultMap);
            }
        }
        return result;
    }

    private int getValue(Coordinate coordinate,List<List<Integer>> mapToWorkWith){
        return mapToWorkWith.get(coordinate.getY()).get(coordinate.getX());
    }
    private void setValue(Coordinate coordinate,int value){
        resultMap.get(coordinate.getY()).set(coordinate.getX(),value);
    }

    public List<List<Integer>> getOriginalMap() {
        return originalMap;
    }

    public List<List<Integer>> getResultMap() {
        return resultMap;
    }
}
