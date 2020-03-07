package vsu.ShellSort;

import java.util.Random;

public class MainShellSort {
    public static void main(String[] args) {
        long start;
        long end;
        Random rn = new Random();
        int[] array = new int[500000];
        int parts = array.length / 2;
        int[] part1 = new int[parts];
        int[] part2;
        if (parts * 2 != array.length) {
            part2 = new int[parts + 1];
        } else part2 = new int[parts];

        for (int i = 0; i < array.length; i++) {
            array[i] = rn.nextInt(10);
            if (i < parts) {
                part1[i] = array[i];
            }
            if (i >= parts) {
                part2[i - parts] = array[i];
            }
        }

        /**
         * Counting up time in one thread and in several
         */
        try {
            start = System.currentTimeMillis();
            shell(array);
            end = System.currentTimeMillis();
            System.out.println("Single-threaded Shell sorting. Time: " + (end - start) + "ms" + "\n");


            start = System.currentTimeMillis();
            ShellSort mss = new ShellSort(part1, part2);
            mss.getThr().join();
            array = mss.getArray();
            end = System.currentTimeMillis();
            System.out.println("Multithreaded Shell sorting. Time: " + (end - start) + "ms" );
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Shell sort
     * @param array
     */
    static void shell(int[] array) {
        int length = array.length;
        int parts = length / 2;
        while (parts > 0) {
            for (int i = 0; i < length - parts; i++) {
                int j = i;
                while ((j >= 0) && array[j] > array[j + parts]) {
                    int temp = array[j];
                    array[j] = array[j + parts];
                    array[j + parts] = temp;
                    j--;
                }
            }
            parts /= 2;
        }
    }
}
