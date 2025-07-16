package tzjanosi.y2024.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData {
        public List<Integer[]> readRestriction(String name){
        String line;
        String[] arrayFromLine;
        Integer[] integerArrayFromLine;
        List<Integer[]> result=new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))) {
            while((line= reader.readLine())!=null){
                arrayFromLine=line.split("[|]");
                integerArrayFromLine=new Integer[]{Integer.valueOf(arrayFromLine[0]),Integer.valueOf(arrayFromLine[1])};
                result.add(integerArrayFromLine);
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
        return result;
    }

    public List<List<Integer>> readPageOrders(String name) {
        String line;
        String[] arrayFromLine;
        List<Integer> listFromLine;
        List<List<Integer>> result=new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))) {
            while((line= reader.readLine())!=null){
                arrayFromLine=line.split(",");
                listFromLine = Arrays.stream(arrayFromLine).map(Integer::valueOf).collect(Collectors.toList());
                result.add(new ArrayList<>(listFromLine));
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
        return result;
    }
}
