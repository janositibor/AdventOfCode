package tzjanosi_temp.y2024.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData {
    private List<List<Integer>> list=new ArrayList<>();
    private String name;

    public ReadData(String name) {
        this.name = name;
        read();
    }

    private void read(){
        String line;
        String[] arrayFromLine;
        List<Integer> listFromLine=new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))){
            while((line= reader.readLine())!=null){
                listFromLine.clear();
                arrayFromLine=line.split(" ");
                listFromLine = Arrays.stream(arrayFromLine).map(Integer::valueOf).collect(Collectors.toList());
                list.add(List.copyOf(listFromLine));
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public List<List<Integer>> getList() {
        return list;
    }
}
