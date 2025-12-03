package tzjanosi.y2025.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LobbyTest {
    @Test
    void calculateTest() {
        ReadData readData = new ReadData("testInput.txt");
        Lobby lobby = new Lobby(readData.getOutput());
        assertEquals(357, lobby.calculate());
    }

    @Test
    void calculateProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Lobby lobby = new Lobby(readData.getOutput());
        assertEquals(17376, lobby.calculate());
    }

}