package TZJanosi.y2024.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadData {
    private String name;
    private String input;

    public ReadData(String name) {
        this.name = name;
        read();
    }

    private void read(){
        String line;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(TZJanosi.y2024.day09.ReadData.class.getResourceAsStream(name)))){
            input=reader.readLine();
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("Can't find the specified file: "+name);
        }
    }

    public String getInput() {
        return input;
    }
}
