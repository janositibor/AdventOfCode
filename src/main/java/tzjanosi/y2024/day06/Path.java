package tzjanosi.y2024.day06;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Trio> pathList = new ArrayList<>();

    public Path() {
    }

    public Path(List<Trio> pathList) {
        this.pathList = new ArrayList<>(pathList);
    }

    public void addPath(Trio position){
        pathList.add(position);
    }
    public boolean alreadyVisited(Trio position){
        return pathList.contains(position);
    }

    public List<Trio> getPathList() {
        return pathList;
    }
}
