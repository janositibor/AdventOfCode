package tzjanosi.y2019.day08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Image {
    private String data;
    private List<Layer> layers = new ArrayList<>();
    private Layer merged;

    public Image(int x, int y, String input) {
        data = input;
        processInput(x, y, input);
    }

    public void merge() {
        int size = layers.get(0).getSize();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(findColor(i));
        }
        int x = layers.get(0).getxLimit();
        int y = layers.get(0).getyLimit();
        merged = new Layer(x, y);
        merged.fillData(sb.toString());
        merged.show();
    }

    private char findColor(int startingIndex) {
        int index = startingIndex;
        int step = layers.get(0).getSize();
        char output = data.charAt(index);
        while (output == '2') {
            index += step;
            output = data.charAt(index);
        }
        return output;
    }

    public int checkSum() {
        Layer min = layers.stream().min(Comparator.comparingInt(Layer::numberOfZeros)).orElseThrow(() -> new IllegalStateException("No layers found"));
        return min.checkSum();
    }

    private void processInput(int x, int y, String input) {
        int layerSize = x * y;
        int inputLength = input.length();
        if (inputLength % layerSize != 0) {
            throw new IllegalArgumentException("The length of input String is not valid: " + input);
        }
        int from = 0;
        while (from < inputLength) {
            String layerData = input.substring(from, from + layerSize);
            from += layerSize;
            Layer layer = new Layer(x, y);
            layer.fillData(layerData);
            layers.add(layer);
        }
    }

    public Layer getMerged() {
        return merged;
    }
}
