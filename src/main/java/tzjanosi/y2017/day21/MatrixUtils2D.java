package tzjanosi.y2017.day21;

public final class MatrixUtils2D {

    private MatrixUtils2D() {
    }

    public static char[][] concatRows(char[][] a, char[][] b) {
        char[][] result = new char[a.length + b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static char[][] concatCols(char[][] a, char[][] b) {
        int rows = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        char[][] result = new char[rows][colsA + colsB];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(a[i], 0, result[i], 0, colsA);
            System.arraycopy(b[i], 0, result[i], colsA, colsB);
        }
        return result;
    }
}
