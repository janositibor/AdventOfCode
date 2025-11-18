package tzjanosi.y2017.day18.part2;

import java.util.Deque;
import java.util.List;

public class Connection {
    private Deque<Long> inputNumbers;
    private Deque<Long> outputNumbers;
    private List<Boolean> inputFlagRun;
    private List<Boolean> outputFlagRun;
    private List<Boolean> inputFlagWait;
    private List<Boolean> outputFlagWait;
    private List<Integer> result;

    public Connection(Deque<Long> inputNumbers, Deque<Long> outputNumbers, List<Boolean> inputFlagRun, List<Boolean> outputFlagRun, List<Boolean> inputFlagWait, List<Boolean> outputFlagWait, List<Integer> result) {
        this.inputNumbers = inputNumbers;
        this.outputNumbers = outputNumbers;
        this.inputFlagRun = inputFlagRun;
        this.outputFlagRun = outputFlagRun;
        this.inputFlagWait = inputFlagWait;
        this.outputFlagWait = outputFlagWait;
        this.result = result;
    }

    public Deque<Long> getInputNumbers() {
        return inputNumbers;
    }

    public Deque<Long> getOutputNumbers() {
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

    public List<Boolean> getInputFlagWait() {
        return inputFlagWait;
    }

    public List<Boolean> getOutputFlagWait() {
        return outputFlagWait;
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

    public void setInputFlagRun(Boolean flag) {
        this.inputFlagRun.set(0, flag);
    }

    public void setOutputFlagRun(Boolean flag) {
        this.outputFlagRun.set(0, flag);
    }

    public void setInputFlagWait(Boolean flag) {
        this.inputFlagWait.set(0, flag);
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
        return getInputNumbers().removeFirst();
    }


    public void addOutputNumber(Register parameter1) {
        getOutputNumbers().addLast(parameter1.getValue());
        incrementResult();
    }
}
