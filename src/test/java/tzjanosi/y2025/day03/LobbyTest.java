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

    @Test
    void calculateMaxValueByDigits2Test() {
        ReadData readData = new ReadData("testInput.txt");
        Lobby lobby = new Lobby(readData.getOutput());
        assertEquals(357L, lobby.calculateMaxValueByDigits(2));
    }

    @Test
    void calculateMaxValueByDigits2ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Lobby lobby = new Lobby(readData.getOutput());
        assertEquals(17376L, lobby.calculateMaxValueByDigits(2));
    }

    @Test
    void calculateMaxValueByDigits12Test() {
        ReadData readData = new ReadData("testInput.txt");
        Lobby lobby = new Lobby(readData.getOutput());
        assertEquals(3121910778619L, lobby.calculateMaxValueByDigits(12));
    }

    @Test
    void calculateMaxValueByDigits12ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Lobby lobby = new Lobby(readData.getOutput());
        assertEquals(172119830406258L, lobby.calculateMaxValueByDigits(12));
    }

}