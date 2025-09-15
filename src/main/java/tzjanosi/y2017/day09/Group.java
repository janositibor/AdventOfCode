package tzjanosi.y2017.day09;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Group parent;
    private int score;
    private List<Group> children = new ArrayList<>();

    public Group(Group parent, int score) {
        this.parent = parent;
        this.score = score;
    }

    public void addChild(Group child) {
        children.add(child);
    }

    public int totalScore() {
        return children.stream().mapToInt(Group::totalScore).sum() + score;
    }

    public Group getParent() {
        return parent;
    }

    public int getScore() {
        return score;
    }
}
