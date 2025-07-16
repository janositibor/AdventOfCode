package tzjanosi_temp.y2024.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData {
    private List<List<Integer>> output=new ArrayList<>();
    private String name;

    public ReadData(String name) {
        this.name = name;
        read();
    }

    private void read(){
        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))) {
            while((line= reader.readLine())!=null){
                String[]  arrayFromLine=line.split("");
                List<Integer> listFromLine = Arrays.stream(arrayFromLine).map(Integer::valueOf).collect(Collectors.toList());
                output.add(listFromLine);
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public List<List<Integer>> getOutput() {
        return output;
    }
}
