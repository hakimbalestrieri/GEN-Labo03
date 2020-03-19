package ch.heigvd.hbcg.lecteursRedacteurs;

public class Redacteur implements Runnable {

    private Controleur controleur;

    public Redacteur(Controleur controleur) {
        if (controleur == null)
            throw new IllegalArgumentException("Controleur nul");
        this.controleur = controleur;
    }

    public void startWrite() {
    }

    public boolean isWaiting() {
        return false;
    }

    public void stopWrite() {
    }

    @Override
    public void run() {
        controleur.writeFile(this);
    }
}
