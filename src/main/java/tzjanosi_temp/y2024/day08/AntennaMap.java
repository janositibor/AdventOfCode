package tzjanosi_temp.y2024.day08;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AntennaMap {
    private Map<Character, List<Coordinate>> antennasMap = new ConcurrentHashMap<>();
    private Coordinate limit=new Coordinate(0,0);

    public void buildAntennasFromLine(int y, String line) {
        setLimit(y,line);
        for (int i = 0; i < line.length(); i++) {
            Character c=line.charAt(i);
            if(c!='.'){
                if (!antennasMap.containsKey(c)) {
                    antennasMap.put(c, new ArrayList<>());
                }
                antennasMap.get(c).add(new Coordinate(i, y));
            }
        }
    }

    private void setLimit(int y, String line) {
        if(limit.getY()<y) {
            limit.setY(y);
        }
        limit.setX(line.length()-1);
    }
     public Set<Coordinate> calculateAntinodes(boolean extended){
         Set<Coordinate> antinodes=new HashSet<>();
         for (Map.Entry<Character, List<Coordinate>> entry : antennasMap.entrySet()) {
             antinodes.addAll(calculateAntinodesForAFrequency(entry.getValue(),extended));
         }
         return antinodes;
     }

    private Set<Coordinate> calculateAntinodesForAFrequency(List<Coordinate> coordinates,boolean extended) {
        Set<Coordinate> antinodes=new HashSet<>();
        for (int i = 0; i < coordinates.size()-1; i++) {
            for (int j = i+1; j < coordinates.size(); j++) {
                antinodes.addAll(coordinates.get(i).getAntinodes(coordinates.get(j)).stream().filter(c->c.isInsideArea(limit)).toList());
                if(extended){
                    antinodes.addAll(getExtendedAntinodes(coordinates.get(i),coordinates.get(j))
                            .stream().filter(c->c.isInsideArea(limit)).toList());
                }
            }
        }
        return antinodes;
    }

    public Set<Coordinate> getExtendedAntinodes(Coordinate a,Coordinate b){
        Set<Coordinate> result=new HashSet<>();
        for (int i = 0; i <= limit.getX(); i++) {
            for (int j = 0; j <= limit.getY(); j++) {
                if((i-a.getX())*(b.getY()-a.getY())==((j-a.getY())*(b.getX()-a.getX()))){
                    result.add(new Coordinate(i,j));
                }
            }

        }
        return result;
    }


    public Map<Character, List<Coordinate>> getAntennasMap() {
        return antennasMap;
    }

    public Coordinate getLimit() {
        return limit;
    }
}
