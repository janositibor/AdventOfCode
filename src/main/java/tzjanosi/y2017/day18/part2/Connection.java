package tzjanosi.y2017.day18.part2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Connection {
    private BlockingQueue<Long> inputNumbers;
    private BlockingQueue<Long> outputNumbers;
    private AtomicInteger inputFlag;
    private AtomicInteger outputFlag;
    private AtomicInteger result;

    public Connection(BlockingQueue<Long> inputNumbers, BlockingQueue<Long> outputNumbers, AtomicInteger inputFlag, AtomicInteger outputFlag, AtomicInteger result) {
        this.inputNumbers = inputNumbers;
        this.outputNumbers = outputNumbers;
        this.inputFlag = inputFlag;
        this.outputFlag = outputFlag;
        this.result = result;
    }


    public BlockingQueue<Long> getInputNumbers() {
        return inputNumbers;
    }

    public BlockingQueue<Long> getOutputNumbers() {
        return outputNumbers;
    }

    public int inputFlagValue() {
        return inputFlag.get();

    }

    public int outputFlagValue() {
        return outputFlag.get();

    }

    public int getResultValue() {
        return result.get();
    }

    public void incrementResult() {
        result.set((result.get() + 1));
    }

    public void setOutputFlag(int flag) {
        this.outputFlag.set(flag);
    }

    public boolean shouldWait() {
        return (inputNumbers.isEmpty() && inputFlagValue() > -1 && (inputFlagValue() > 0 || !outputNumbers.isEmpty()));
    }

    public boolean hasInputNumbers() {
        return !getInputNumbers().isEmpty();
    }

    public long readInputNumbers() {
        long value = Long.MIN_VALUE;
        try {
            value = inputNumbers.take();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return value;
    }

    public void addOutputNumber(Register parameter1) {
        long value = parameter1 == null ? Long.MIN_VALUE : parameter1.getValue();
        try {
            outputNumbers.put(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (parameter1 != null) {
            incrementResult();
        }
    }
}
