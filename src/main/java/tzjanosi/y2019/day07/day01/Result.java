package tzjanosi.y2019.day07.day01;

import java.util.Optional;

public class Result {
    private boolean stop;
    private Optional<Long> output;
    private int shift;

    public Result(boolean stop, Optional<Long> output, int shift) {
        this.stop = stop;
        this.output = output;
        this.shift = shift;
    }

    public boolean isStop() {
        return stop;
    }

    public int getShift() {
        return shift;
    }

    public Optional<Long> getOutput() {
        return output;
    }
}
