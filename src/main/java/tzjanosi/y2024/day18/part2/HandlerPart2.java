package tzjanosi.y2024.day18.part2;

import java.util.List;

public class HandlerPart2 {
    private ReadData readData;
    private List<String> totalInput;
    private int startingCount;
    private int size;

    public HandlerPart2(String filename, int size, int startingCount) {
        this.startingCount = startingCount;
        this.size = size;
        readData = new ReadData(filename);
        totalInput = readData.getOutput();
    }

    public String getFirstBlockingBrick() {
        for (int i = startingCount; i < totalInput.size(); i++) {
            LabyrinthPart2 labyrinthPart2 = new LabyrinthPart2(size, totalInput, i);
            int result = labyrinthPart2.calculateWay();
            if (result == -1) {
                return labyrinthPart2.getLastBrick();
            }
        }
        return "";
    }

}
