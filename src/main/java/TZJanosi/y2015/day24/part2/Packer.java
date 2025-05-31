package TZJanosi.y2015.day24.part2;

import java.util.*;

public class Packer {
    private List<Container> footSlots = new ArrayList<>();
    private int numberOfSlots;
    private List<Integer> originalPackages;
    private Sleigh originalSleigh;
    private int minNumberOfPackagesInFootSlots;
    private int weightLimit;
    private boolean found = false;

    public Packer(Sleigh originalSleigh) {
        this.originalSleigh = originalSleigh;
        originalPackages = new ArrayList<>(originalSleigh.getAllPackages());
        numberOfSlots = originalSleigh.getNumberOfSlots();
        weightLimit = originalSleigh.getWeightLimit();
        calculateMinNumberOfPackagesInFootSlots(originalSleigh.getMaxReachableIncrement());
    }

    private void calculateMinNumberOfPackagesInFootSlots(List<Integer> maxReachableIncrement) {
        int i = 1;
        while (minNumberOfPackagesInFootSlots == 0) {
            if (maxReachableIncrement.get(i) > weightLimit) {
                minNumberOfPackagesInFootSlots = i;
            }
            i++;
        }
    }

    public long calculateMinQe() {
        fillFootSlots();
        return getQeFromFootSlots();
    }

    private long getQeFromFootSlots() {
        return footSlots.stream().mapToLong(c -> c.getQe()).min().getAsLong();
    }

    private void fillFootSlots() {
        int i = minNumberOfPackagesInFootSlots;
        while (footSlots.size() == 0) {
            for (int j = 0; j < originalPackages.size(); j++) {
                fillFootSlotsPackageNumber(i, j, new HashSet<>());
            }
            i++;
        }
    }

    private void fillFootSlotsPackageNumber(int packageNumber, int from, Set<Integer> packages) {
        Set<Integer> extendedPackages = new HashSet<>(packages);
        extendedPackages.add(originalPackages.get(from));
        if (extendedPackages.size() == packageNumber) {
            Container container = new Container(extendedPackages);
            if (container.getWeight() == weightLimit && issPossibleToBalanceRemainings(container)) {
                footSlots.add(container);
            }
            return;
        }
        for (int i = from + 1; i < originalPackages.size(); i++) {
            fillFootSlotsPackageNumber(packageNumber, i, extendedPackages);
        }
    }

    private boolean issPossibleToBalanceRemainings(Container container) {
        List<Integer> remainingPackages = new ArrayList<>(originalPackages);
        remainingPackages.removeAll(container.getPackages());
        Sleigh sleigh = new Sleigh(remainingPackages, numberOfSlots - 1);
        return isPossibleToPack(sleigh);

    }

    public boolean isPossibleToPack(Sleigh sleigh) {
        if (sleigh.isValid()) {
            if (sleigh.isPacked()) {
                found = true;
            } else {
                replaceNextElement(sleigh);
            }
        }
        return found;
    }

    private void replaceNextElement(Sleigh sleigh) {
        Sleigh child;
        for (int i = 0; i < sleigh.getSlots().size() && found == false; i++) {
            if (sleigh.isValid()) {
                child = new Sleigh(sleigh);
                child.replaceNextElementIntoASlot(i);
                if (child.isPacked() && child.isValid()) {
                    found = true;
                }
                replaceNextElement(child);
            }
        }
    }

    @Override
    public String toString() {
        return "Packer{" +
                "footSlots=" + footSlots +
                ", numberOfSlots=" + numberOfSlots +
                ", originalPackages=" + originalPackages +
                ", minNumberOfPackagesInFootSlots=" + minNumberOfPackagesInFootSlots +
                ", weightLimit=" + weightLimit +
                '}';
    }
}
