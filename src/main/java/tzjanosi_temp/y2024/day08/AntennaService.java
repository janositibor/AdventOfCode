package tzjanosi_temp.y2024.day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AntennaService {
    private AntennaMap antennaMap=new AntennaMap();
    private Set<Coordinate> antinodes=new HashSet<>();

    public void readInputListOfStrings(List<String> input){
        for (int i = 0; i < input.size(); i++) {
            antennaMap.buildAntennasFromLine(i, input.get(i));
        }
    }

    public void setAntinodes(boolean extended) {
        this.antinodes = antennaMap.calculateAntinodes(extended);
    }
    public int countAntinodes(){
        return antinodes.size();
    }

    public AntennaMap getAntennaMap() {
        return antennaMap;
    }

    public Set<Coordinate> getAntinodes() {
        return antinodes;
    }
}
