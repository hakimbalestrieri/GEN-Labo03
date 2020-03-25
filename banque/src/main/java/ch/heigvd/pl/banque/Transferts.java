//Créé par Hakim Balestrieri et Christian Gomes

package ch.heigvd.pl.banque;

import java.util.Random;

public class Transferts extends Thread {

    private Banque banque;

    public Transferts(Banque banque) {
        if(banque == null) {
            throw new IllegalArgumentException("Banque est nul");
        }
        this.banque = banque;
    }

    @Override
    public void run() {
        final int N = 1000;
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            int crediteur = rand.nextInt(banque.getNbComptes());
            int debiteur = rand.nextInt(banque.getNbComptes());
            int montant = rand.nextInt(500);
            banque.transfert(debiteur, crediteur, montant);
        }
    }
}
