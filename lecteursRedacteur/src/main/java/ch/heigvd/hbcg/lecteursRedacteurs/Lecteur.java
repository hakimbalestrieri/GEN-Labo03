//Créé par Hakim Balestrieri et Christian Gomes

package ch.heigvd.hbcg.lecteursRedacteurs;

public class Lecteur implements Runnable {

    //Membre privé
    private Controleur controleur;

    //Constructeur
    public Lecteur(Controleur controleur) {
        if (controleur == null) {
            throw new IllegalArgumentException("Controleur nul");
        }
        this.controleur = controleur;
    }

    public boolean isWaiting() {
        return controleur.isWaiting(this);
    }

    synchronized public void startRead() throws InterruptedException {
        new Thread(this).start();
        //attente obligatoire
        Thread.sleep(0, 1);
    }

    synchronized public void stopRead() throws InterruptedException {
        controleur.stopRead(this);
        Thread.sleep(0, 1);
    }

    @Override
    public void run() {
        controleur.readFile(this);
    }
}
