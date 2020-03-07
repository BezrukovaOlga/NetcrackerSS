package vsu.MultiplyMatrix;

public class Multiply extends Thread {
    private int[][] firstArray;
    private int[][] secondArray;
    private int[][] result;
    private int begin;
    private int end;
    private int n;
    private int c;

    Multiply(int[][] firstArray, int[][] secondArray, int[][] result, int begin, int end, int n, int c) {
        this.firstArray = firstArray;
        this.secondArray = secondArray;
        this.result = result;
        this.begin = begin;
        this.end = end;
        this.n = n;
        this.c = c;
    }

    /**
     * Matrix multiplication
     */
    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < c; j++) {
                result[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    result[i][j] += firstArray[i][k] * secondArray[k][j];
                }
            }
        }
    }
}
