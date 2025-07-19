package tzjanosi.y2016.day04;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Room> rooms = new ArrayList<>();

    public Service(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    private void processLine(String line) {
        int positionOfLastDash = line.lastIndexOf('-');
        String name = line.substring(0, positionOfLastDash);
        int positionOfSquareBrace = line.indexOf('[');
        int sectorId = Integer.parseInt(line.substring(positionOfLastDash + 1, positionOfSquareBrace));
        String checkSum = line.substring(positionOfSquareBrace + 1, line.length() - 1);
        Room room = new Room(name, sectorId, checkSum);
        rooms.add(room);
    }

    public int calculateSumOfSectorIDsForRealRooms() {
        return rooms.stream()
                .filter(Room::isValid)
                .mapToInt(Room::getSectorID)
                .sum();
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
