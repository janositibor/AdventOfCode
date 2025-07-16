package tzjanosi.y2015.day20;

public class Village {
    public int findTheLuckyPart1(int targetToReach) {
        House house;
        int houseNumber = 0;
        do {
            houseNumber++;
            house = new House(houseNumber);
        }
        while (house.getPresentsValuePart1() < targetToReach);

        return houseNumber;
    }

    public int findTheLuckyPart2(int targetToReach) {
        House house;
        int houseNumber = 0;
        do {
            houseNumber++;
            house = new House(houseNumber);
        }
        while (house.getPresentsValuePart2() < targetToReach);

        return houseNumber;
    }
}
