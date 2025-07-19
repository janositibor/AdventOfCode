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
        room.decryptName();
        rooms.add(room);
    }

    public int sectorIDForNorthPoleObjects() {
        return rooms.stream()
                .filter(Room::isValid)
//                .peek(r-> System.out.println(r.getDecryptedName()))
                .filter(r -> "northpole object storage".equals(r.getDecryptedName()))
                .mapToInt(Room::getSectorID)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No room found ..."));
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
