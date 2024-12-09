package TZJanosi.y2024.day08;

import java.util.*;

public class AntennaMap {
    private Map<Character, List<Coordinate>> antennaMap=new HashMap<>();
    private Coordinate limit=new Coordinate(0,0);

    public void getAntennasFromLine(int y, String line){
        setLimit(y,line);
        for (int i = 0; i < line.length(); i++) {
            Character c=line.charAt(i);
            if(c!='.'){
                if(!antennaMap.containsKey(c)){
                    antennaMap.put(c,new ArrayList<>());
                }
                antennaMap.get(c).add(new Coordinate(i,y));
            }
        }
    }

    private void setLimit(int y, String line) {
        if(limit.getY()<y) {
            limit.setY(y);
        }
        limit.setX(line.length()-1);
    }
     public Set<Coordinate> calculateAntinodes(){
         Set<Coordinate> antinodes=new HashSet<>();
         for (Map.Entry<Character, List<Coordinate>> entry:antennaMap.entrySet()) {
             antinodes.addAll(calculateAntinodesForAFrequency(entry.getValue()));
         }
         return antinodes;
     }

    private Set<Coordinate> calculateAntinodesForAFrequency(List<Coordinate> coordinates) {
        Set<Coordinate> antinodes=new HashSet<>();
        for (int i = 0; i < coordinates.size()-1; i++) {
            for (int j = i+1; j < coordinates.size(); j++) {
                antinodes.addAll(coordinates.get(i).getAntinodes(coordinates.get(j)).stream().filter(c->c.isInsideArea(limit)).toList());
            }
        }
        return antinodes;
    }


    public Map<Character, List<Coordinate>> getAntennaMap() {
        return antennaMap;
    }

    public Coordinate getLimit() {
        return limit;
    }
}
