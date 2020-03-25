//Créé par Hakim Balestrieri et Christian Gomes

package ch.heigvd.hbcg.lecteursRedacteurs;

import java.util.HashSet;
import java.util.Set;

public class Controleur {

    //Déclarations de variables privées
    //Polymorphisme si l'on veut, par le futur, ajouter un autre type de liste dérivé de la classe Set
    private Set attenteRedacteurs = new HashSet<Redacteur>();
    private Set attenteLecteurs = new HashSet<Lecteur>();
    private Set lecteursActifs = new HashSet<Lecteur>();
    private Redacteur redacteurActif;

    synchronized void writeFile(Redacteur redacteur) {
            while (redacteurActif != null || !lecteursActifs.isEmpty()) {
                try {
                    //Nous n'avons pas besoin de vérifier si attenteRedacteurs contient
                    // déjà l'élément car HashSet le fait déjà
                    attenteRedacteurs.add(redacteur);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        attenteRedacteurs.remove(redacteur);
        this.redacteurActif = redacteur;
    }

    synchronized boolean isWaiting(Object personne) {
        if (personne.getClass() == Lecteur.class) {
            return attenteLecteurs.contains(personne);
        } else {
            return attenteRedacteurs.contains(personne);
        }
    }

    synchronized void readFile(Lecteur lecteur) {
        while (!attenteRedacteurs.isEmpty() || redacteurActif != null) {
            try {
                attenteLecteurs.add(lecteur);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        attenteLecteurs.remove(lecteur);
        lecteursActifs.add(lecteur);
    }

    synchronized public void stopRead(Lecteur lecteur) {
        lecteursActifs.remove(lecteur);
        if (lecteursActifs.isEmpty()) {
            notifyAll();
        }
    }

    synchronized public void stopWrite() {
        redacteurActif = null;
        notifyAll();
    }
}
