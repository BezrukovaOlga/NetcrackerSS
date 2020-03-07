package vsu.ShellSort;

public class Threads implements Runnable {
    private int[] array;
    private Thread thr;

    Threads(int[] array) {
        super();
        this.array = array;
        this.thr = new Thread(this);
        thr.start();
    }



    Thread getThr() {
        return thr;
    }

    public void run() {
        MainShellSort.shell(array);
    }
}
