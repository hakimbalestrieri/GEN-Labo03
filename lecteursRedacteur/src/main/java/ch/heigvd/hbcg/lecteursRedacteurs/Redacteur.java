//Créé par Hakim Balestrieri et Christian Gomes

package ch.heigvd.hbcg.lecteursRedacteurs;

public class Redacteur implements Runnable {

    //Membre privé
    private Controleur controleur;

    //Constructeur
    public Redacteur(Controleur controleur) {
        if (controleur == null) {
            throw new IllegalArgumentException("Controleur nul");
        }
        this.controleur = controleur;
    }

    public boolean isWaiting() {
        return controleur.isWaiting(this);
    }

    synchronized public void startWrite() throws InterruptedException {
        new Thread(this).start();
        Thread.sleep(0, 1);
    }

    synchronized public void stopWrite() throws InterruptedException {
        controleur.stopWrite();
        Thread.sleep(0, 1);
    }

    @Override
    public void run() {
        controleur.writeFile(this);
    }
}
