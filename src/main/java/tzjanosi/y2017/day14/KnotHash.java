package tzjanosi.y2017.day14;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class KnotHash {
    private int startingPosition;
    private int skipSize;
    private int[] garland;
    private List<Integer> lengths = new ArrayList<>();
    private List<Integer> denseHash = new ArrayList<>();

    public KnotHash(String input) {
        garland = IntStream.range(0, 256).toArray();
        processInput(input);
    }

    private void processInput(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.US_ASCII);
        for (int i = 0; i < bytes.length; i++) {
            lengths.add(Integer.valueOf(bytes[i]));
        }
        addAdditionalLengths();
    }

    private void addAdditionalLengths() {
        int[] lengthsToAdd = {17, 31, 73, 47, 23};
        for (int i = 0; i < lengthsToAdd.length; i++) {
            lengths.add(lengthsToAdd[i]);
        }
    }

    public String createHash() {
        multipleKnot(64);
        createDenseHash();
        String hexString = hashFromDenseHash();
        return createBinStringFromHex(hexString);
    }

    private String createBinStringFromHex(String hexString) {
        BigInteger hex = new BigInteger(hexString, 16);
        String binString = hex.toString(2);
        binString = String.format("%128s", binString).replace(' ', '0');
        return binString;
    }

    private String hashFromDenseHash() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < denseHash.size(); i++) {
            output.append(String.format("%02x", denseHash.get(i)));
        }
        return output.toString();
    }

    private void createDenseHash() {
        for (int i = 0; i < 16; i++) {
            denseHash.add(executeXor(i));
        }
    }

    private Integer executeXor(int startHex) {
        int startIndex = 16 * startHex;
        int output = garland[startIndex];
        for (int j = 1; j < 16; j++) {
            int index = startIndex + j;
            output ^= garland[index];
        }
        return output;
    }

    private void multipleKnot(int count) {
        for (int i = 0; i < count; i++) {
            knot();
        }
    }

    private void knot() {
        for (int i = 0; i < lengths.size(); i++) {
            int length = lengths.get(i);
            mirror(length);
            startingPosition = getPosition(length + skipSize);
            skipSize++;
        }
    }

    private int getPosition(int shift) {
        int position = startingPosition + shift;
        while (position >= garland.length) {
            position -= garland.length;
        }
        return position;
    }

    private void mirror(int length) {
        int steps = length / 2;
        for (int i = 0; i < steps; i++) {
            int position1Index = getPosition(i);
            int position2Index = getPosition(length - 1 - i);
            int temp = garland[position1Index];
            garland[position1Index] = garland[position2Index];
            garland[position2Index] = temp;
        }
    }
}
