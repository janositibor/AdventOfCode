package tzjanosi_temp.y2024.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HikeForPathSearch {
    private List<List<Integer>> originalMap;
    private List<List<Integer>> resultMap=new ArrayList<>();
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

    public List<List<Integer>> getOriginalMap() {
        return originalMap;
    }

    public List<List<Integer>> getResultMap() {
        return resultMap;
    }
}
