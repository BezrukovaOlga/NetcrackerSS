package vsu.MultiplyMatrix;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int m = 5000;
        int n = 250;
        int c = 1700;
        int[][] firstArray = new int[m][n];
        int[][] secondArray = new int[n][c];
        int[][] result = new int[m][c];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                firstArray[i][j] = 2;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                secondArray[i][j] = 3;
            }
        }

        /**
         * Multithreaded counting
         */
        long workTime = System.currentTimeMillis();
        ArrayList<Multiply> list = new ArrayList<Multiply>();
        Multiply multiply;
        for (int i = 0; i < m; ) {
            multiply = new Multiply(firstArray, secondArray, result, i, i += 1250, n, c);
            list.add(multiply);
            multiply.start();
        }

        for (Multiply one : list) {
            try {
                one.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Multithreading Computing Speed = " + (System.currentTimeMillis() - workTime));

        /**
         * Single thread counting
         */
        workTime = System.currentTimeMillis();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < c; j++) {
                result[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    result[i][j] += firstArray[i][k] * secondArray[k][j];
                }

            }
        }
        System.out.println("Single thread calculation speed = " + (System.currentTimeMillis() - workTime));
    }
}
