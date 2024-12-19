package TZJanosi.y2024.day12;

public class Region {
    private char value;
    private int area=0;
    private int perimeter=0;
    private int cost=0;

    public Region(char value) {
        this.value = value;
    }

    public void addPoint(int numberOfGoodNeighbours){
        area++;
        perimeter+=4-numberOfGoodNeighbours;
    }

    public void calculateCost(){
        cost=area*perimeter;
    }

    public int getCost() {
        return cost;
    }
}
