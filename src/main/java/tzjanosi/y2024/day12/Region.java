package tzjanosi.y2024.day12;

public class Region {
    private int area;
    private int perimeter;
    private int cost;
    private int numberOfFence;
    private int discountCost;

    public void addPoint(int numberOfGoodNeighbours){
        area++;
        perimeter+=4-numberOfGoodNeighbours;
    }

    public void calculateCost(){
        cost=area*perimeter;
        discountCost=area*numberOfFence;
    }

    public void setNumberOfFence(int numberOfFence) {
        this.numberOfFence = numberOfFence;
    }

    public int getCost() {
        return cost;
    }

    public int getDiscountCost() {
        return discountCost;
    }
}
