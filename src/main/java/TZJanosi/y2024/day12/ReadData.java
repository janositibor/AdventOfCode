package TZJanosi.y2024.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData {
    private List<String> output=new ArrayList<>();
    private String name;

    public ReadData(String name) {
        this.name = name;
        read();
    }

    private void read(){
        String line;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(TZJanosi.y2024.day12.ReadData.class.getResourceAsStream(name)))){
            while((line= reader.readLine())!=null){
                output.add(line);
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public List<String> getOutput() {
        return output;
    }
}
