package tzjanosi.y2017.day18.part2;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Connection {
    private BlockingQueue<Long> inputNumbers;
    private BlockingQueue<Long> outputNumbers;
    private List<Boolean> inputFlagRun;
    private List<Boolean> outputFlagRun;
    private List<Boolean> inputFlagWait;
    private List<Boolean> outputFlagWait;
    private List<Integer> result;

    public Connection(BlockingQueue<Long> inputNumbers, BlockingQueue<Long> outputNumbers, List<Boolean> inputFlagRun, List<Boolean> outputFlagRun, List<Boolean> inputFlagWait, List<Boolean> outputFlagWait, List<Integer> result) {
        this.inputNumbers = inputNumbers;
        this.outputNumbers = outputNumbers;
        this.inputFlagRun = inputFlagRun;
        this.outputFlagRun = outputFlagRun;
        this.inputFlagWait = inputFlagWait;
        this.outputFlagWait = outputFlagWait;
        this.result = result;
    }

    public BlockingQueue<Long> getInputNumbers() {
        return inputNumbers;
    }

    public BlockingQueue<Long> getOutputNumbers() {
        return outputNumbers;
    }

    public List<Boolean> getInputFlagRun() {
        return inputFlagRun;
    }

    public Boolean outputFlagRunValue() {
        return outputFlagRun.get(0);

    }

    public List<Boolean> getOutputFlagRun() {
        return outputFlagRun;
    }

    public List<Integer> getResult() {
        return result;
    }

    public Integer getResultValue() {
        return result.get(0);
    }

    public void incrementResult() {
        result.set(0, (result.get(0) + 1));
    }

    public void setOutputFlagRun(Boolean flag) {
        this.outputFlagRun.set(0, flag);
    }

    public void setOutputFlagWait(Boolean flag) {
        this.outputFlagWait.set(0, flag);
    }

    public boolean shouldWait() {
        return (inputNumbers.isEmpty() && inputFlagRun.get(0) && (!inputFlagWait.get(0) || !outputNumbers.isEmpty()));
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
