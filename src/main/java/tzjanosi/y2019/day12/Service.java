package tzjanosi.y2019.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
    private List<Moon> moons = new ArrayList<>();
    private List<Moon> originalmoons = new ArrayList<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int energy() {
        return moons.stream().mapToInt(Moon::energy).sum();
    }

    public long findPeriod() {
        int periodX = findPeriodForOneCoordinate(Vector::getX);
        int periodY = findPeriodForOneCoordinate(Vector::getY);
        int periodZ = findPeriodForOneCoordinate(Vector::getZ);
        int[] inputNumbers = {periodX, periodY, periodZ};
//        System.out.println(Arrays.toString(inputNumbers));
        return calculateLCMOfMultipleNumbers(inputNumbers);
    }

    private long gcd(long input1, long input2) {
        long a = input1;
        long b = input2;
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private long calculateLCMOfMultipleNumbers(int[] inputNumbers) {
        long lcm = inputNumbers[0];
        for (int i = 1; i < inputNumbers.length; i++) {
            long next = lcm / gcd(lcm, inputNumbers[i]) * inputNumbers[i];
            lcm = next;
        }
        return lcm;
    }

    private void reset() {
        moons.clear();
        for (int i = 0; i < originalmoons.size(); i++) {
            Moon moon = new Moon(originalmoons.get(i));
            moons.add(moon);
        }
    }

    private int findPeriodForOneCoordinate(ToIntFunction<Vector> getCoordinateFunction) {
        reset();
        int output = 0;
        do {
            oneStep();
            output++;
        } while (!areTheyTheSameForAllMoonsAndOneCoordinate(getCoordinateFunction));
        return output;
    }

    public void steps(int count) {
//        printMoons(moons);
        for (int i = 0; i < count; i++) {
            oneStep();
        }
//        printMoons(moons);
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

    private boolean areTheyTheSameForAllMoonsAndOneCoordinate(ToIntFunction<Vector> getCoordinateFunction) {
        boolean output = true;
        int counter = 0;
        while (counter < moons.size() && output) {
            if (!areTheyTheSameForOneMoonAndOneCoordinate(moons.get(counter), originalmoons.get(counter), getCoordinateFunction)) {
                output = false;
            }
            counter++;
        }
        return output;
    }

    private boolean areTheyTheSameForOneMoonAndOneCoordinate(Moon moon1, Moon moon2, ToIntFunction<Vector> getCoordinateFunction) {
        return getCoordinateFunction.applyAsInt(moon1.getPosition()) == getCoordinateFunction.applyAsInt(moon2.getPosition()) && getCoordinateFunction.applyAsInt(moon1.getVelocity()) == getCoordinateFunction.applyAsInt(moon2.getVelocity());
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
        Moon moon2 = new Moon(coordinates);
        originalmoons.add(moon2);
    }

    public void printMoons(List<Moon> moonsToPrint) {
        for (int i = 0; i < moonsToPrint.size(); i++) {
            System.out.println(moonsToPrint.get(i));
        }
        System.out.println();
    }
}
