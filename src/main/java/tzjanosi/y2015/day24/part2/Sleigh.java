package tzjanosi.y2015.day24.part2;

import java.util.*;

public class Sleigh {
    private List<Integer> allPackages;
    private int numberOfSlots;
    private List<Integer> packagesToWorkWith;
    private int weightLimit;
    private List<Container> slots = new ArrayList<>();
    private boolean valid = true;
    private List<Integer> maxReachableIncrement = new ArrayList<>();


    public Sleigh(List<Integer> allPackages, int numberOfSlots) {
        this.allPackages = List.copyOf(allPackages);
        this.numberOfSlots = numberOfSlots;
        packagesToWorkWith = new ArrayList<>(this.allPackages);
        buildSlots(numberOfSlots);
        setWeightLimit();
        buildMaxReachableIncrement();
    }

    private int calculateMaxReachableIncrement(int numberOfPackages) {
        return allPackages.stream()
                .sorted(Comparator.reverseOrder())
//                .peek((x) -> System.out.println(numberOfPackages+" -> "+x))
                .limit(numberOfPackages)
//                .peek((x) -> System.out.println("limit -> "+x))
                .mapToInt(x -> x)
                .sum();
    }

    private void buildMaxReachableIncrement() {
        int result = 0;
        int i = 1;
        maxReachableIncrement.add(result);
        while (result <= weightLimit && i < allPackages.size()) {
            result = calculateMaxReachableIncrement(i);
            maxReachableIncrement.add(result);
            i++;
        }
    }

    private void buildSlots(int numberOfSlots) {
        for (int i = 0; i < numberOfSlots; i++) {
            slots.add(new Container());
        }
    }

    public Sleigh(Sleigh original) {
        weightLimit = original.weightLimit;
        valid = original.valid;
        maxReachableIncrement = new ArrayList<>(original.maxReachableIncrement);
        allPackages = List.copyOf(original.allPackages);
        packagesToWorkWith = new ArrayList<>(original.packagesToWorkWith);
        slots = new ArrayList<>();
        for (int i = 0; i < original.slots.size(); i++) {
            slots.add(new Container(original.slots.get(i)));
        }
    }

    @Override
    public String toString() {
        return "Sleigh{" +
                "packagesToWorkWith=" + packagesToWorkWith +
                ", slots=" + slots +
                '}';
    }

    public Sleigh replaceNextElementIntoASlot(int i) {
        if (packagesToWorkWith.isEmpty()) {
            throw new IllegalStateException("No more package to replace!");
        }
        if (i >= slots.size()) {
            throw new IllegalArgumentException("There is no slot no: " + i);
        }
        int value = packagesToWorkWith.get(packagesToWorkWith.size() - 1);
        packagesToWorkWith.remove(packagesToWorkWith.size() - 1);
        slots.get(i).addPackage(value);
        setValid();
        return this;
    }

    public boolean isPacked() {
        return packagesToWorkWith.isEmpty();
    }

    public void setValid() {

        if (slots.stream().anyMatch(container -> container.getWeight() > weightLimit)) {
            valid = false;
        }

    }

    private void setWeightLimit() {
        int totalWeight = allPackages.stream().mapToInt(x -> x).sum();
        if (totalWeight % slots.size() != 0) {
            valid = false;
            throw new IllegalArgumentException(String.format("No possibility to divide into %d equal groups. Total weight is: %d", slots.size(), totalWeight));
        }
        weightLimit = totalWeight / slots.size();
    }

//        public Sleigh getParent() {
//        return parent;
//    }
//
//    public void setParent(Sleigh parent) {
//        this.parent = parent;
//    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public boolean isValid() {
        return valid;
    }

    public List<Container> getSlots() {
        return slots;
    }

    public List<Integer> getAllPackages() {
        return allPackages;
    }

    public List<Integer> getMaxReachableIncrement() {
        return maxReachableIncrement;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }
}
