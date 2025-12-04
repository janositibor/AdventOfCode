package tzjanosi.y2017.day18.part2;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Duet {
    private List<String> input;
    private Solo solo0;
    private Solo solo1;
    private BlockingQueue<Long> numbersTo0 = new LinkedBlockingQueue<>();
    private BlockingQueue<Long> numbersTo1 = new LinkedBlockingQueue<>();
    private AtomicInteger flagTo0 = new AtomicInteger(1);
    private AtomicInteger flagTo1 = new AtomicInteger(1);
    private AtomicInteger resultFrom0 = new AtomicInteger(0);
    private AtomicInteger resultFrom1 = new AtomicInteger(0);

    public Duet(List<String> input) {
        this.input = input;

        Connection connection0 = new Connection(numbersTo0, numbersTo1, flagTo0, flagTo1, resultFrom0);
        solo0 = new Solo(0, input, connection0);

        Connection connection1 = new Connection(numbersTo1, numbersTo0, flagTo1, flagTo0, resultFrom1);
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
        return resultFrom1.get();
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

    public BlockingQueue<Long> getNumbersTo0() {
        return numbersTo0;
    }

    public BlockingQueue<Long> getNumbersTo1() {
        return numbersTo1;
    }
}
