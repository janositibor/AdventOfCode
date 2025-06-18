package TZJanosi.y2015.day20;

public class Village {
    public int findTheLucky(int targetToReach) {
        House house;
        int houseNumber = 0;
        do {
            houseNumber++;
            house = new House(houseNumber);
        }
        while (house.getPresentsValue() < targetToReach);

        return houseNumber;
    }
}
