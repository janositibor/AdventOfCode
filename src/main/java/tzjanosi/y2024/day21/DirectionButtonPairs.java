package tzjanosi.y2024.day21;

import java.util.Objects;

public class DirectionButtonPairs {
    private ButtonForDirectionalPad start;
    private ButtonForDirectionalPad end;

    public DirectionButtonPairs(ButtonForDirectionalPad start, ButtonForDirectionalPad end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DirectionButtonPairs that = (DirectionButtonPairs) o;
        return start == that.start && end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
