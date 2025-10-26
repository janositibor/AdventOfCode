package tzjanosi.y2017.day13;

public class Layer {
    private int position;
    private int depth;
    private int period;

    public Layer(int position, int depth) {
        this.position = position;
        this.depth = depth;
        period = 2 * (depth - 1);
    }

    public int severity() {
        if ((position) % period == 0) {
            return position * depth;
        }
        return 0;
    }

    public boolean isCaught(int delay) {
        return (position + delay) % period == 0;
    }

}
