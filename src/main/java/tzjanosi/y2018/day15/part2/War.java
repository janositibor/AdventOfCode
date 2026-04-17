package tzjanosi.y2018.day15.part2;

import java.util.List;

public class War {
    private List<String> input;
    private Board board;

    public War(List<String> input) {
        this.input = input;
    }

    public int cheat() {
        int output;
        for (int i = 4; i < 201; i++) {
            board = new Board(input, i);
            output = board.game();
            if (output > 0) {
                return output;
            }
        }
        return -1;
    }
}
