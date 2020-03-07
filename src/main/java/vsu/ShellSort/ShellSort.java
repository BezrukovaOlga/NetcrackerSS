package vsu.ShellSort;

class ShellSort implements Runnable {
    private int[] firstPartArray;
    private int[] secondPartArray;
    private int[] array;
    private Thread thr;

    ShellSort(int[] firstPartArray, int[] secondPartArray) {
        super();
        this.firstPartArray = firstPartArray;
        this.secondPartArray = secondPartArray;
        this.thr = new Thread(this);
        thr.start();
    }

    /**
     * Threads start and wait for them to finish. At the end there is a merger.
     */
    public void run() {
        try {
            Threads one = new Threads(firstPartArray);
            Threads two = new Threads(secondPartArray);
            one.getThr().join();
            two.getThr().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        array = merge(firstPartArray, secondPartArray);
    }

    /**
     *
     * @param firstPartArray - The first part of the sorted array
     * @param secondPartArray - The second part of the sorted array
     * The method merges the arrays sorted in streams
     * @return result array
     */
    private int[] merge(int[] firstPartArray, int[] secondPartArray) {
        int length = firstPartArray.length + secondPartArray.length;
        int[] merged = new int[length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < length; i++) {
            if (i1 == firstPartArray.length) {
                merged[i] = secondPartArray[i2++];
            } else if (i2 == secondPartArray.length) {
                merged[i] = firstPartArray[i1++];
            } else {
                if (firstPartArray[i1] < secondPartArray[i2]) {
                    merged[i] = firstPartArray[i1++];
                } else {
                    merged[i] = secondPartArray[i2++];
                }
            }
        }
        return merged;
    }

    /**
     * Get thread
     * @return thread
     */
    Thread getThr() {
        return thr;
    }

    /**
     * Get sorted
     * @return result array
     */
    int[] getArray() {
        return array;
    }
}
