package ch.heigvd.pl.banque;

public class Transferts extends Thread {

    public static void main(String[] args) {
        Transferts thread = new Transferts();
        thread.start();

    }



    public void run() {
        final int N = 10;
        for (int i = 0; i < N; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
