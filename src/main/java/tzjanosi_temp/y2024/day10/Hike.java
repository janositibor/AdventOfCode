package tzjanosi_temp.y2024.day10;

import java.util.*;

public class Hike {
    private List<List<HikeCoordinate>> hikeMap=new ArrayList<>();
    private List<Coordinate> shifts=List.of(new Coordinate(0,-1),new Coordinate(-1,0),new Coordinate(0,1),new Coordinate(1,0));
    private Map<String,Integer> params=Map.ofEntries(Map.entry("min",0),Map.entry("max",9));
    private Coordinate limit=new Coordinate(0,0);

    public Hike(List<List<Integer>> originalMap) {
        int lengthOfOuterlist= originalMap.size();
        int lengthOfInnerlist= originalMap.get(0).size();
        for (int i = 0; i < lengthOfOuterlist; i++) {
            List<HikeCoordinate> line=new ArrayList<>();
            hikeMap.add(line);
            for (int j = 0; j < originalMap.get(0).size(); j++) {
                Coordinate coordinate=new Coordinate(j,i);
                int height=originalMap.get(i).get(j);
                HikeCoordinate hikeCoordinate=new HikeCoordinate(coordinate,height);
                line.add(hikeCoordinate);
            }

        }
        limit.setX(lengthOfInnerlist-1);
        limit.setY(lengthOfOuterlist-1);
    }


    public void numberOfPathsFrom(int from) {
        for (int i = 0; i < hikeMap.size(); i++) {
            for (int j = 0; j < hikeMap.get(0).size(); j++) {
                Coordinate coordinate=new Coordinate(j,i);
                if(getPoint(coordinate).getHeight()==from){
//                    System.out.println(coordinate);
                    getPoint(coordinate).setAvailablePeaks(numberOfNeighbourPaths(getPoint(coordinate),from+1));
                }
            }
        }
    }

    public Map<String,Integer> calculatePaths(){
        for (int i = params.get("max"); i >= params.get("min") ; i--) {
            numberOfPathsFrom(i);
//            System.out.println(resultMap);
        }
        return Map.ofEntries(Map.entry("peaks",countPaths()),Map.entry("paths",countDistinctPaths())) ;
    }

    private int countPaths() {
        int result=0;
        for (int i = 0; i < hikeMap.size(); i++) {
            for (int j = 0; j < hikeMap.get(0).size(); j++) {
                Coordinate coordinate=new Coordinate(j,i);
                if(getPoint(coordinate).getHeight()==params.get("min")){
                    result+=getPoint(coordinate).getAvailablePeaks().size();
                }
            }
        }
        return result;
    }

    private int countDistinctPaths() {
        int result=0;
        for (int i = 0; i < hikeMap.size(); i++) {
            for (int j = 0; j < hikeMap.get(0).size(); j++) {
                Coordinate coordinate=new Coordinate(j,i);
                if(getPoint(coordinate).getHeight()==params.get("min")){
                    result+=getPoint(coordinate).getNumberOfAvailablePeaks();
                }
            }
        }
        return result;
    }

    private Set<Coordinate> numberOfNeighbourPaths(HikeCoordinate origo, int seekFor){
        Set<Coordinate> result=new HashSet<>();
        if(seekFor==params.get("max")+1){
            origo.setNumberOfAvailablePeaks(1);
            return Set.of(origo.getCoordinate());
        }
        for (Coordinate shift:shifts) {
            Coordinate coordinate=origo.getCoordinate().add(shift);

            if(coordinate.isInsideArea(limit) && getPoint(coordinate).getHeight()==seekFor){
                HikeCoordinate hikeCoordinate=getPoint(coordinate);
                result.addAll(hikeCoordinate.getAvailablePeaks());
                origo.setNumberOfAvailablePeaks(origo.getNumberOfAvailablePeaks()+ hikeCoordinate.getNumberOfAvailablePeaks());
            }
        }
        return result;
    }


    private HikeCoordinate getPoint(Coordinate coordinate){
        return hikeMap.get(coordinate.getY()).get(coordinate.getX());
    }

    public List<List<HikeCoordinate>> getHikeMap() {
        return hikeMap;
    }
}
