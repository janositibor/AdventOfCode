package tzjanosi.y2024.day11;

import java.util.Set;

public class Stone {
    private long value;

    public Stone(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String getValueAsString(){
        return String.valueOf(value);
    }

    private int getStringLength(){
        return getValueAsString().length();
    }

    private boolean isEven(){
        return getStringLength()%2==0;
    }
    private String getHalfOfString(int index){
        int halfLength=getStringLength()/2;
        int from=index*halfLength;
        int to=(index+1)*halfLength;
        return getValueAsString().substring(from,to);
    }

    private int getHalfAsInt(int index){
        return Integer.parseInt(getHalfOfString(index));
    }

    public Set<Stone> next(){
        if(value==0){
            return Set.of(new Stone(1));
        }
        if(isEven()){
            return Set.of(new Stone(getHalfAsInt(0)),new Stone(getHalfAsInt(1)));
        }
        return Set.of(new Stone(value*2024));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
