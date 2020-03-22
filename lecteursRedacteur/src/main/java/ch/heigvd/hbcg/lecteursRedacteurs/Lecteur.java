package ch.heigvd.hbcg.lecteursRedacteurs;

public class Lecteur implements Runnable {

    private Controleur controleur;

    public Lecteur(Controleur controleur) {

        if (controleur == null) {
            throw new IllegalArgumentException("Controleur nul");
        } else {
            this.controleur = controleur;
        }
    }

    public void startRead() throws InterruptedException {
        synchronized (this) {
            new Thread(this).start();
            Thread.sleep(1);
        }
    }

    public boolean isWaiting() {
        return controleur.isWaiting(this);
    }

    public void stopRead() throws InterruptedException {
        synchronized (this) {
            controleur.stopRead(this);
            Thread.sleep(1);
        }
    }

    @Override
    public void run() {
        controleur.readFile(this);
    }
}
