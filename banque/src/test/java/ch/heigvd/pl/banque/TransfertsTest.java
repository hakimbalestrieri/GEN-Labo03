package ch.heigvd.pl.banque;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TransfertsTest {


    private Banque banque;
    private Random rand;



    @BeforeEach
    void setUp() {
        banque = new Banque(10);
        rand = new Random();
    }

    @Test
    public void transfertTest() throws InterruptedException {

        final int NB_TRANSFERTS = 1000;
        ArrayList<Transferts> transfertsList = new ArrayList<Transferts>();

        for(int i = 0; i < NB_TRANSFERTS; i++) {
            transfertsList.add(new Transferts(banque));
            transfertsList.get(i).start();
        }

        //insérer join avec synchronized
        for(Transferts transfert : transfertsList)
            transfert.join();

        assertTrue(banque.consistent());
    }
}