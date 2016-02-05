package com.adi.app.pai.filosofi;

public class Furculite {
    private boolean[] furculite = new boolean[5];

    public Furculite() {
        for (int i = 0; i < 5; i++) {
            furculite[i] = false;
        }
    }

    public synchronized  void cereFurculite(int fUnu, int fDoi) {
        while(furculite[fUnu] || furculite[fDoi]) {
            try {
                wait();
            }
            catch (InterruptedException ie) {
                System.err.println("Exceptie de la cerut furculitele!");
            }
        }
        furculite[fUnu] = true;
        furculite[fDoi] = true;
    }

    public synchronized  void elibereazaFurculite(int fUnu, int fDoi) {
        furculite[fUnu] = false;
        furculite[fDoi] = false;
        notify();
    }
}
