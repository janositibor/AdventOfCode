package tzjanosi.y2018.day07;

public class Agent {
    private int delay;
    private char workWith;
    private int finishTime;

    public Agent(int delay) {
        this.delay = delay;
    }

    public void work(char job, int now) {
        workWith = job;
        finishTime = now + job - 'A' + 1 + delay;
    }

    public boolean isIdle(int time) {
        return time >= finishTime;
    }

    public char getWorkWith() {
        return workWith;
    }

    public void setWorkWith(char workWith) {
        this.workWith = workWith;
    }
}
