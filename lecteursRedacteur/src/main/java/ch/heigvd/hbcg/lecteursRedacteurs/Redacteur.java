package ch.heigvd.hbcg.lecteursRedacteurs;

public class Redacteur implements Runnable {

    private Controleur controleur;

    public Redacteur(Controleur controleur) {
        if (controleur == null) {
            throw new IllegalArgumentException("Controleur nul");
        }
        else {
            this.controleur = controleur;
        }
    }

    public boolean isWaiting() {
        return controleur.isWaiting(this);
    }

    public void startWrite() throws InterruptedException {
        synchronized (this) { //un seul rédacteur à la fois
            new Thread(this).start();
            Thread.sleep(1);
        }
    }

    public void stopWrite() throws InterruptedException {
        synchronized (this) {
            controleur.stopWrite();
            Thread.sleep(1);
        }
    }

    @Override
    public void run() {
        controleur.writeFile(this);
    }
}
