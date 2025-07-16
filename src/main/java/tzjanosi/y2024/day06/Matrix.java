package tzjanosi.y2024.day06;

public final class Matrix {
    private Matrix() {
    }

    public static char[][] rotateCounterClockWiseChar(final char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        char[][] result= new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[n-j-1][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static char[][] newMatrix(final char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        char[][] result= new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[i][j];
            }
        }
        return result;
    }
}
