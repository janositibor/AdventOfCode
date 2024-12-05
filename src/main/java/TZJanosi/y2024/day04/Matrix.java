package TZJanosi.y2024.day04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Matrix {
    public static <T> T[][] transpose(final T[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        T[][] result= (T[][]) new Object[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static char[][] transposeChar(final char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        char[][] result= new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static char[][] shiftUpGraduallyChar(final char[][] matrix) {
        return transposeChar(shiftLeftGraduallyChar(transposeChar(matrix)));
    }

    public static char[][] shiftDownGraduallyChar(final char[][] matrix) {
        return transposeChar(shiftRightGraduallyChar(transposeChar(matrix)));
    }

    public static char[][] shiftRightGraduallyChar(final char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        char[][] result= new char[m][m+n-1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m+n-1; j++) {
                result[i][j] = 'o';
            }
            for (int j = 0; j < n; j++) {
                result[i][j+i] = matrix[i][j];
            }
        }
        return result;
    }


    public static char[][] shiftLeftGraduallyChar(final char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        char[][] result= new char[m][m+n-1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m+n-1; j++) {
                result[i][j] = 'o';
            }
            for (int j = 0; j < n; j++) {
                result[i][j+m-1-i] = matrix[i][j];
            }
        }
        return result;
    }
}
