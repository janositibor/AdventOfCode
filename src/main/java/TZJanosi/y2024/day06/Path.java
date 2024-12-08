package TZJanosi.y2024.day06;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Trio> path=new ArrayList<>();

    public Path() {
    }

    public Path(List<Trio> path) {
        this.path = new ArrayList<>(path);
    }

    public void addPath(Trio position){
        path.add(position);
    }
    public boolean alreadyVisited(Trio position){
        return path.contains(position);
    }

    public List<Trio> getPath() {
        return path;
    }
}
