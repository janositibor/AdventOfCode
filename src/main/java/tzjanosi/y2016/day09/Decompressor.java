package tzjanosi.y2016.day09;

public class Decompressor {
    private String input;
    private String decompressed = "";

    public Decompressor(String input) {
        this.input = input;
    }

    public int decompress() {
        String stringToProcess = input;
        while (stringToProcess.contains("(")) {
            stringToProcess = process(stringToProcess);
        }
        decompressed = decompressed.concat(stringToProcess);
        return decompressed.length();
    }

    private String process(String stringToProcess) {
        int markerStart = stringToProcess.indexOf('(');
        int markerEnd = stringToProcess.indexOf(')');
        if (markerStart > 0) {
            decompressed = decompressed.concat(stringToProcess.substring(0, markerStart));
        }
        int[] lengthOfRepeatedSequenceAndNumberOfRepetition = calculateLengthOfRepeatedSequenceAndNumberOfRepetition(stringToProcess.substring(markerStart + 1, markerEnd));
        int lengthOfRepeatedSequence = lengthOfRepeatedSequenceAndNumberOfRepetition[0];
        int numberOfRepetition = lengthOfRepeatedSequenceAndNumberOfRepetition[1];
        doRepeation(stringToProcess, markerEnd, lengthOfRepeatedSequence, numberOfRepetition);
        return stringToProcess.substring(markerEnd + 1 + lengthOfRepeatedSequence);
    }

    private void doRepeation(String stringToProcess, int markerEnd, int lengthOfRepeatedSequence, int numberOfRepetition) {
        int endOfRepeatedSequence = Math.min(markerEnd + 1 + lengthOfRepeatedSequence, stringToProcess.length());
        String sequenceToRepeat = stringToProcess.substring(markerEnd + 1, endOfRepeatedSequence);
        decompressed = decompressed.concat(sequenceToRepeat.repeat(numberOfRepetition));
    }

    private int[] calculateLengthOfRepeatedSequenceAndNumberOfRepetition(String input) {
        String[] numbers = input.split("x");
        return new int[]{Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])};
    }

    public String getDecompressed() {
        return decompressed;
    }
}
