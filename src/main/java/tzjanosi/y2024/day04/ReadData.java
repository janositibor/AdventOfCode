package tzjanosi.y2024.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    private char[][] inputMatrix;
    private String name;

    public ReadData(String name) {
        this.name = name;
        read();
    }
    private void read(){
        String line;
        char[] arrayFromLine;
        List<char[]> tempContainerForCharArrays=new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(name)))) {
            while((line= reader.readLine())!=null){
                arrayFromLine=line.toCharArray();
                tempContainerForCharArrays.add(arrayFromLine);
            }
            inputMatrix=new char[tempContainerForCharArrays.size()][];
            for (int i = 0; i < tempContainerForCharArrays.size(); i++) {
                inputMatrix[i]= tempContainerForCharArrays.get(i);
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public char[][] getInputMatrix() {
        return inputMatrix;
    }
}
