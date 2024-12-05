package TZJanosi.y2024.day04;

public class Crossword {

    private char[][] inputMatrix;
    private String keyword;

    private int numberOfFoundKeyword=0;
    private int numberOfXShapes=0;

    public Crossword(char[][] inputMatrix, String keyword) {
        this.inputMatrix = inputMatrix;
        this.keyword = keyword;
        findNumberOfKeywords(inputMatrix);
        findXShapes();
    }

    private void findXShapes(){
        int m = inputMatrix.length;
        int n = inputMatrix[0].length;

        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if(inputMatrix[i][j]=='A'){
                    if(isXShape(i,j)){
                        numberOfXShapes++;
                    }
                }
            }
        }
    }

    private boolean isXShape(int i, int j) {
        String diag1=new String(new char[]{inputMatrix[i-1][j-1],inputMatrix[i][j],inputMatrix[i+1][j+1]});
        String diag2=new String(new char[]{inputMatrix[i-1][j+1],inputMatrix[i][j],inputMatrix[i+1][j-1]});

        return((diag1.equals("MAS")||diag1.equals("SAM")) && (diag2.equals("MAS")||diag2.equals("SAM")));
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

    public int getNumberOfXShapes() {
        return numberOfXShapes;
    }
}
