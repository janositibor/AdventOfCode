package tzjanosi.y2015.day06.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    private String prefix = "/tzjanosi/y2015/day06/";
    private List<String> output = new ArrayList<>();
    private String name;

    public ReadData(String name) {
        this.name = prefix + name;
        read();
    }

    private void read() {
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))) {
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can't find the specified file: " + name);
        }
    }

    public List<String> getOutput() {
        return output;
    }
}
