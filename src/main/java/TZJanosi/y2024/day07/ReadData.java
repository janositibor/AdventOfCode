package TZJanosi.y2024.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData {
    private String name;

    private List<Long> expectedResults=new ArrayList<>();
    private List<List<Integer>> operands=new ArrayList<>();

    public ReadData(String name) {
        this.name = name;
        read();
    }
    private void read(){
        String line;
//        String[] arrayFromLine;
//        List<Integer> listFromLine=new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(TZJanosi.y2024.day07.ReadData.class.getResourceAsStream(name)))){
            while((line= reader.readLine())!=null){
//                listFromLine.clear();
                String[]  arrayFromLine=line.split(": ");
                expectedResults.add(Long.parseLong(arrayFromLine[0]));
                String[] arrayOfOperands=arrayFromLine[1].split(" ");
                List<Integer> listFromLine= Arrays.stream(arrayOfOperands).map(s->Integer.valueOf(s)).collect(Collectors.toList());
                operands.add(List.copyOf(listFromLine));
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public List<Long> getExpectedResults() {
        return expectedResults;
    }

    public List<List<Integer>> getOperands() {
        return operands;
    }
}
