package ch.heigvd.hbcg.lecteursRedacteurs;

import java.util.HashSet;
import java.util.Set;

public class Controleur {

    private Set attenteRedacteurs = new HashSet<Redacteur>();
    private Set attenteLecteurs = new HashSet<Lecteur>();
    private Set lecteursActifs = new HashSet<Lecteur>();
    private Redacteur redacteurActif;

    public void writeFile(Redacteur redacteur) {
        synchronized (this) {
            while (redacteurActif != null || !lecteursActifs.isEmpty()) {
                try {
                    attenteRedacteurs.add(redacteur); //pas besoin de vérifier si contient car Set le fait automatiquement
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        attenteRedacteurs.remove(redacteur);
        this.redacteurActif = redacteur;
    }

    public boolean isWaiting(Object personne) {
        synchronized (this) {
            if (personne.getClass() == Lecteur.class)
                return attenteLecteurs.contains(personne);

            else
                return attenteRedacteurs.contains(personne);

        }
    }

    public void readFile(Lecteur lecteur) {
        synchronized (this) {
            while (!attenteRedacteurs.isEmpty() || redacteurActif != null) {
                try {
                    if (!attenteLecteurs.contains(lecteur)) {
                        attenteLecteurs.add(lecteur);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
        attenteLecteurs.remove(lecteur);
        lecteursActifs.add(lecteur);
    }

    public void stopRead(Lecteur lecteur) {
        synchronized (this) {
            lecteursActifs.remove(lecteur);
            if (lecteursActifs.isEmpty()) {
                notifyAll();
            }
        }

    }

    public void stopWrite() {
        synchronized (this) {
            redacteurActif = null;
            notifyAll();
        }
    }
}
