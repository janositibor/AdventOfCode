package tzjanosi.y2019.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
    private List<Moon> moons = new ArrayList<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int energy() {
        return moons.stream().mapToInt(Moon::energy).sum();
    }

    public void steps(int count) {
//        printMoons();
        for (int i = 0; i < count; i++) {
            oneStep();
        }
//        printMoons();
    }

    private void oneStep() {
        List<Moon> nextMoons = new ArrayList<>();
        for (int i = 0; i < moons.size(); i++) {
            Moon actualMoon = moons.get(i);
            Vector force = calculateGravityForAMoon(actualMoon);
            Moon nextMoon = new Moon(actualMoon);
            nextMoon.step(force);
            nextMoons.add(nextMoon);
        }
        moons = nextMoons;
    }

    private Vector calculateGravityForAMoon(Moon actualMoon) {
        int forcex = calculateGravityForOneCoordinate(actualMoon, Moon::getX);
        int forcey = calculateGravityForOneCoordinate(actualMoon, Moon::getY);
        int forcez = calculateGravityForOneCoordinate(actualMoon, Moon::getZ);
        return new Vector(forcex, forcey, forcez);
    }

    private int calculateGravityForOneCoordinate(Moon actualMoon, ToIntFunction<Moon> getCoordinateFunction) {
        int actualPosition = getCoordinateFunction.applyAsInt(actualMoon);
        int less = (int) moons.stream().filter(m -> getCoordinateFunction.applyAsInt(m) < actualPosition).count();
        int great = (int) moons.stream().filter(m -> getCoordinateFunction.applyAsInt(m) > actualPosition).count();
        return great - less;
    }


    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    private void processLine(String line) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(line);
        List<Integer> coordinates = new ArrayList<>();
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            coordinates.add(number);
        }
        Moon moon = new Moon(coordinates);
        moons.add(moon);
    }

    public void printMoons() {
        for (int i = 0; i < moons.size(); i++) {
            System.out.println(moons.get(i));
        }
        System.out.println();
    }
}
