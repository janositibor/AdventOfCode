package tzjanosi.y2016.day08;

import java.util.ArrayList;
import java.util.List;

public class Display {
    private List<Pixel> pixels = new ArrayList<>();
    private Coordinate limit;

    public Display(Coordinate limit) {
        this.limit = limit;
        buildPixels();
    }

    private void buildPixels() {
        for (int i = 0; i < limit.getX(); i++) {
            for (int j = 0; j < limit.getY(); j++) {
                Pixel pixel = new Pixel(new Coordinate(i, j));
                pixels.add(pixel);
            }
        }
    }

    public void turnOn(Coordinate coordinate) {
        pixels.stream().filter(p -> p.inTheArea(coordinate)).forEach(Pixel::turnOn);
    }

    public void row(int whichRow, int shift) {
        findPixelsInRow(whichRow).stream().forEach(p -> shiftPixelColumn(p, shift));
    }

    public void column(int whichColumn, int shift) {
        findPixelsInColumn(whichColumn).stream().forEach(p -> shiftPixelRow(p, shift));
    }

    private List<Pixel> findPixelsInRow(int row) {
        return pixels.stream().filter(p -> p.inTheRow(row)).toList();
    }

    private List<Pixel> findPixelsInColumn(int column) {
        return pixels.stream().filter(p -> p.inTheColumn(column)).toList();
    }

    private int newPosition(int position, int shift, int limit) {
        int output = position + shift;
        if (output >= limit) {
            output -= limit;
        }
        return output;
    }

    private void shiftPixelRow(Pixel pixel, int shift) {
        pixel.setRow(newPosition(pixel.getRow(), shift, limit.getY()));
    }

    private void shiftPixelColumn(Pixel pixel, int shift) {
        pixel.setColumn(newPosition(pixel.getColumn(), shift, limit.getX()));
    }

    public int calculateNumberOfTurnedOnPixels() {
        return (int) pixels.stream().filter(Pixel::isTurnedOn).count();
    }

    public List<String> showResult() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < limit.getY(); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < limit.getX(); j++) {
                Pixel dummy = new Pixel(new Coordinate(j, i));
                Pixel actual = pixels.stream()
                        .filter(p -> p.equals(dummy))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No pixel found!"));
                if (actual.isTurnedOn()) {
                    line.append('*');
                } else {
                    line.append(' ');
                }
            }
            System.out.println(line.toString());
            output.add(line.toString());
        }
        return output;
    }
}
