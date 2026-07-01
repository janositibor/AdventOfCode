package tzjanosi.y2019.day08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Image {
    private List<Layer> layers = new ArrayList<>();

    public Image(int x, int y, String input) {
        processInput(x, y, input);
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
}
