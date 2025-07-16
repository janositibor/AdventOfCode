package tzjanosi_temp.y2024.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    private List<String> list=new ArrayList<>();
    private String name;

    public ReadData(String name) {
        this.name = name;
        read();
    }

    private void read(){
        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))) {
            while((line= reader.readLine())!=null){
                list.add(line);
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public List<String> getList() {
        return list;
    }
}
