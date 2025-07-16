package tzjanosi_temp.y2024.day04;

public class Line {
    private StringBuilder lineStringBuilder;
    private String keyword;
    private int numberOfKeywordInLine;

    public Line(char[] inputCharArray, String keyword) {
        this.keyword = keyword;
        lineStringBuilder = new StringBuilder(new String(inputCharArray));

        setNumberOfKeywordInLine(lineStringBuilder);


        setNumberOfKeywordInLine(lineStringBuilder.reverse());
    }

    private void setNumberOfKeywordInLine(StringBuilder lineToCheck){
        int result=0;
        int searchFrom=0;
        int index;
        while((index=(lineToCheck.indexOf(keyword,searchFrom)))>=0){
            result++;
            searchFrom=index+1;
        }
        numberOfKeywordInLine += result;
    }

    public int getNumberOfKeywordInLine() {
        return numberOfKeywordInLine;
    }
}
