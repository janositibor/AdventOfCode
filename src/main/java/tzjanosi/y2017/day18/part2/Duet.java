package tzjanosi.y2017.day18.part2;

import java.util.*;

public class Duet {
    private List<String> input;
    private Solo solo0;
    private Solo solo1;
    private Deque<Long> numbersTo0 = new ArrayDeque<>();
    private Deque<Long> numbersTo1 = new ArrayDeque<>();
    private List<Boolean> runFlagTo0 = new ArrayList<>(List.of(true));
    private List<Boolean> runFlagTo1 = new ArrayList<>(List.of(true));
    private List<Boolean> waitFlagTo0 = new ArrayList<>(List.of(false));
    private List<Boolean> waitFlagTo1 = new ArrayList<>(List.of(false));
    private List<Integer> resultFrom0 = new ArrayList<>(List.of(0));
    private List<Integer> resultFrom1 = new ArrayList<>(List.of(0));

    public Duet(List<String> input) {
        this.input = input;

        Connection connection0 = new Connection(numbersTo0, numbersTo1, runFlagTo0, runFlagTo1, waitFlagTo0, waitFlagTo1, resultFrom0);
        solo0 = new Solo(0, input, connection0);

        Connection connection1 = new Connection(numbersTo1, numbersTo0, runFlagTo1, runFlagTo0, waitFlagTo1, waitFlagTo0, resultFrom1);
        solo1 = new Solo(1, input, connection1);
    }

    public int exec() {
        Thread thread1 = new Thread(solo0);
        Thread thread2 = new Thread(solo1);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ie) {
            throw new IllegalArgumentException("ajaj", ie);
        }
        return resultFrom1.get(0);
    }

    public List<String> getInput() {
        return input;
    }

    public Solo getSolo0() {
        return solo0;
    }

    public Solo getSolo1() {
        return solo1;
    }

    public Deque<Long> getNumbersTo0() {
        return numbersTo0;
    }

    public Deque<Long> getNumbersTo1() {
        return numbersTo1;
    }

    public List<Boolean> getRunFlagTo0() {
        return runFlagTo0;
    }

    public List<Boolean> getRunFlagTo1() {
        return runFlagTo1;
    }

    public List<Integer> getResultFrom0() {
        return resultFrom0;
    }

    public List<Integer> getResultFrom1() {
        return resultFrom1;
    }
}
