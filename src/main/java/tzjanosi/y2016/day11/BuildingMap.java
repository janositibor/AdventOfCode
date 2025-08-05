package tzjanosi.y2016.day11;

import java.util.*;

public class BuildingMap {
    private List<Person> people = new ArrayList<>();
    private int elevator = 1;
    private int lowestLevel = 1;
    private int steps;
    private int idMark;

    public BuildingMap(List<String> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            processLine(i + 1, input.get(i).replaceAll("\\.", "").replaceAll(",", "").replaceAll("-compatible", ""));
        }
        createPeopleFrom(people);
    }

    public BuildingMap(BuildingMap original) {

        for (int i = 0; i < original.getPeople().size(); i++) {
            Person person = new Person(original.getPeople().get(i));
            people.add(person);

        }
        elevator = original.elevator;
        lowestLevel = original.lowestLevel;
        steps = original.steps;
    }

    private void createPeopleFrom(List<Person> originalPeopleList) {
        List<Pair> temp = createPairsFrom(originalPeopleList);
        Collections.sort(temp);
        fillPeopleWithPairValues(temp);
    }

    private void fillPeopleWithPairValues(List<Pair> input) {
        people = new ArrayList<>();
        int mark = elevator;
        for (int i = 0; i < input.size(); i++) {
            Pair actualPair = input.get(i);
            people.add(actualPair.getMan());
            people.add(actualPair.getWoman());
            mark = 10 * ((mark * 10) + actualPair.getMan().getLevel()) + actualPair.getWoman().getLevel();
        }
        idMark = mark;
    }

    private List<Pair> createPairsFrom(List<Person> originalPeopleList) {
        List<Pair> output = new ArrayList<>();
        for (int i = 0; i < originalPeopleList.size(); i++) {
            Person personToAdd = new Person(originalPeopleList.get(i));
            String id = personToAdd.getId();
            if (output.contains(new Pair(id))) {
                Pair actualPair = getPairByID(output, id).get();
                actualPair.addPartnerPublic(personToAdd);
            } else {
                output.add(new Pair(personToAdd));
            }
        }
        return output;
    }

    private Optional<Pair> getPairByID(List<Pair> set, String id) {
        for (Pair actual : set) {
            if (actual.getId().equals(id)) {
                return Optional.of(actual);
            }
        }
        return Optional.empty();
    }

    public boolean equalState(BuildingMap other) {
        return people.equals(other.people) && elevator == other.elevator;
    }

    public boolean move(List<Person> peopleToMove, Direction direction) {
        steps++;
        List<Person> peopleFromLevel = peopleFromLevel(elevator);
        for (int i = 0; i < peopleToMove.size(); i++) {
            Person personToMove = findPerson(peopleFromLevel, peopleToMove.get(i));
            if (direction.equals(Direction.UP)) {
                personToMove.moveUp();
            } else {
                personToMove.moveDown();
            }
        }
        elevator += direction.getValue();
        createPeopleFrom(people);
        lowestLevel = calculateLowestLevel();
        return isValid();
    }

    private int calculateLowestLevel() {
        return people.stream().mapToInt(Person::getLevel).min().orElseThrow(() -> new IllegalStateException("No person ..."));
    }


    private Person findPerson(List<Person> peopleFromLevel, Person person) {
        return peopleFromLevel.stream()
                .filter(p -> p.equals(person))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No person found: %s", person.toString())));
    }

    public boolean isValid() {
        for (int i = lowestLevel; i <= 4; i++) {
            if (!isValidLevel(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidLevel(int level) {
        List<Person> men = menFromLevel(level);
        List<Person> women = womenFromLevel(level);
        if (men.isEmpty() || women.isEmpty()) {
            return true;
        }
        for (int i = 0; i < women.size(); i++) {
            String id = women.get(i).getId();
            if (men.stream().noneMatch(p -> p.getId().equals(id))) {
                return false;
            }
        }
        return true;
    }

    public boolean isSuccess() {
        return peopleFromLevel(4).size() == people.size();
    }

    public List<Direction> calculateDirectionToLevel() {
        List<Direction> output = new ArrayList<>();
        if (elevator > lowestLevel) {
            output.add(Direction.DOWN);
        }
        if (elevator < 4) {
            output.add(Direction.UP);
        }

        return output;
    }

    private List<Person> menFromLevel(int level) {
        return peopleFromLevel(level).stream().filter(Person::isMan).toList();
    }

    private List<Person> womenFromLevel(int level) {
        return peopleFromLevel(level).stream().filter(p -> !p.isMan()).toList();
    }

    private List<Person> peopleFromLevel(int level) {
        return people.stream().filter(p -> p.getLevel() == level).toList();
    }

    public List<List<Person>> peopleToMove() {
        List<List<Person>> output = new ArrayList<>();
        output.addAll(getSinglesToMove());
        output.addAll(getDoublesToMove());
        return output;
    }

    public List<List<Person>> getDoublesToMove() {
        List<List<Person>> output = new ArrayList<>();
        List<Person> peopleFromLevel = peopleFromLevel(elevator);
        for (int i = 0; i < peopleFromLevel.size() - 1; i++) {
            for (int j = i + 1; j < peopleFromLevel.size(); j++) {
                List<Person> listToAdd = new ArrayList<>();
                listToAdd.add(new Person(peopleFromLevel.get(i)));
                listToAdd.add(new Person(peopleFromLevel.get(j)));
                output.add(listToAdd);
            }
        }
        return output;
    }

    public List<List<Person>> getSinglesToMove() {
        return peopleFromLevel(elevator).stream().map(p -> List.of(new Person(p))).toList();
    }

    private void processLine(int level, String line) {
        String[] words = line.split(" ");
        int numberOfWords = words.length;
        int numberOfPeopleInLine = numberOfWords == 7 ? 1 : (numberOfWords - 5) / 3;
        int index = 5;
        for (int i = 1; i <= numberOfPeopleInLine; i++) {
            String id = words[index];
            boolean man = "generator".equals(words[index + 1]);
            Person person = new Person(id, man, level);
            people.add(person);
            index += 3;
            if (i == numberOfPeopleInLine - 1) {
                index++;
            }
        }
    }

    public List<Person> getPeople() {
        return people;
    }

    public int getSteps() {
        return steps;
    }

    public int getElevator() {
        return elevator;
    }

    public int getIdMark() {
        return idMark;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BuildingMap that = (BuildingMap) o;
        return elevator == that.elevator && Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(people, elevator);
    }

    @Override
    public String toString() {
        return "BuildingMap{" +
                "people=" + people +
                ", elevator=" + elevator +
                '}';
    }
}
