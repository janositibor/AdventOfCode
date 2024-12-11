package TZJanosi.y2024.day10;

import java.util.Set;

public class HikeCoordinate {
    private Coordinate coordinate;
    private int height;
    private Set<Coordinate> availablePeaks;
    private int numberOfAvailablePeaks=0;


    public HikeCoordinate(Coordinate coordinate, int height) {
        this.coordinate = coordinate;
        this.height = height;
    }

    public void addPeaks(Set<Coordinate> peaksToadd){
        availablePeaks.addAll(peaksToadd);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getHeight() {
        return height;
    }

    public Set<Coordinate> getAvailablePeaks() {
        return availablePeaks;
    }

    public void setAvailablePeaks(Set<Coordinate> availablePeaks) {
        this.availablePeaks = availablePeaks;
    }

    public int getNumberOfAvailablePeaks() {
        return numberOfAvailablePeaks;
    }

    public void setNumberOfAvailablePeaks(int numberOfAvailablePeaks) {
        this.numberOfAvailablePeaks = numberOfAvailablePeaks;
    }

    @Override
    public String toString() {
        return String.valueOf(height);
    }
}
