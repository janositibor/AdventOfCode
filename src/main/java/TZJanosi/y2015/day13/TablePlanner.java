package TZJanosi.y2015.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TablePlanner {
    private List<String> foundPlace = new ArrayList<>();
    private List<String> lookForPlace = new ArrayList<>();
    private List<Couple> couples = new ArrayList<>();
    private int happiness;

    public TablePlanner(TablePlanner original) {
        foundPlace = new ArrayList<>(original.foundPlace);
        lookForPlace = new ArrayList<>(original.lookForPlace);
        couples = new ArrayList<>(original.couples);
        happiness = original.happiness;
    }

    public TablePlanner(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String input) {
        String s = input.substring(0, input.length() - 1);
        String[] parts = s.split(" ");
        Couple couple = createCoupleFromWords(parts);
        addToCouples(couple);
    }

    private void addToCouples(Couple couple) {
        if (couples.contains(couple)) {
            updateCouple(couple);
        } else {
            couples.add(couple);
        }
    }

    private void updateCouple(Couple couple) {
        Set<String> names = couple.getNames();
        List<String> namesAsAList = names.stream().toList();
        Couple saved = findCouple(namesAsAList.get(0), namesAsAList.get(1));
        saved.updateHappiness(couple.getHappiness());
    }

    private Couple createCoupleFromWords(String[] parts) {
        int happiness = Integer.parseInt(parts[3]);
        if (parts[2].equals("lose")) {
            happiness *= -1;
        }
        String name1 = parts[0];
        String name2 = parts[10];
        addToLookForPlace(name1, name2);
        return new Couple(name1, name2, happiness);
    }

    private void addToLookForPlace(String location1, String location2) {
        addToLookForPlace(location1);
        addToLookForPlace(location2);
    }

    private void addToLookForPlace(String location) {
        if (!lookForPlace.contains(location)) {
            lookForPlace.add(location);
        }
    }

    public List<Couple> getCouples() {
        return couples;
    }

    public List<String> getFoundPlace() {
        return foundPlace;
    }

    public List<String> getLookForPlace() {
        return lookForPlace;
    }

    public int getHappiness() {
        return happiness;
    }

    public void sit(String name) {
        Couple couple;
        if (!foundPlace.isEmpty()) {
            couple = findCouple(foundPlace.get(foundPlace.size() - 1), name);
            happiness += couple.getHappiness();
            if (foundPlace.size() > 1) {
                deleteCouplesWith(foundPlace.get(foundPlace.size() - 1));
            }
        }
        foundPlace.add(name);
        lookForPlace.remove(name);
        if (lookForPlace.isEmpty()) {
            couple = findCouple(foundPlace.get(0), name);
            happiness += couple.getHappiness();
            deleteCouplesWith(name);
        }
    }

    private void deleteCouplesWith(String name) {
        List<Couple> couplesToDelete = couples.stream()
                .filter(w -> w.containsName(name))
                .toList();
        couples.removeAll(couplesToDelete);
    }

    public Couple findCouple(String name1, String name2) {
        return couples.stream()
                .filter(w -> w.containsNames(name1, name2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No couple found between %s and %s", name1, name2)));
    }

    @Override
    public String toString() {
        return "TablePlanner{" +
                "foundPlace=" + foundPlace +
                ", lookForPlace=" + lookForPlace +
                ", couples=" + couples +
                ", happiness=" + happiness +
                '}';
    }
}
