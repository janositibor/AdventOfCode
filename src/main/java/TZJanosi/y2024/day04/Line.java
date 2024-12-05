package TZJanosi.y2024.day04;

public class Line {
    private char[] inputCharArray;
    private StringBuilder line;
    private String keyword;
    private int numberOfKeywordInLine=0;

    public Line(char[] inputCharArray, String keyword) {
        this.inputCharArray = inputCharArray;
        this.keyword = keyword;
        line=new StringBuilder(new String(inputCharArray));

        setNumberOfKeywordInLine(line);


        setNumberOfKeywordInLine(line.reverse());
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
