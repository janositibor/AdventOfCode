package TZJanosi.y2024.day04;

public class Crossword {

    private char[][] inputMatrix;
    private String keyword;

    private int numberOfFoundKeyword=0;
    private int foundX=0;

    public Crossword(char[][] inputMatrix, String keyword) {
        this.inputMatrix = inputMatrix;
        this.keyword = keyword;
        findNumberOfKeywords(inputMatrix);
    }

    private int findNumberOfHorizontalKeywords(char[][] matrix){
        Line line;
        int result=0;
        for (int i = 0; i < matrix.length; i++) {
            line=new Line(matrix[i],keyword);
            result+= line.getNumberOfKeywordInLine();
        }
        return result;
    }

    public void findNumberOfKeywords(char[][] matrix){
        int result=0;
        result+=findNumberOfHorizontalKeywords(matrix);
        result+=findNumberOfHorizontalKeywords(Matrix.transposeChar(matrix));
        result+=findNumberOfHorizontalKeywords(Matrix.shiftUpGraduallyChar(matrix));
        result+=findNumberOfHorizontalKeywords(Matrix.shiftDownGraduallyChar(matrix));

        numberOfFoundKeyword=result;
    }

    public int getNumberOfFoundKeyword() {
        return numberOfFoundKeyword;
    }
}
