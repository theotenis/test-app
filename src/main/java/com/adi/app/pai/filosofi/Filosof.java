package com.adi.app.pai.filosofi;

import java.awt.*;


public class Filosof extends Thread {
    private Graphics g;
    private Furculite masa;
    private int fUnu, fDoi;

    public Filosof(String nume, Graphics g, Furculite masa, int fUnu, int fDoi) {
        super(nume);
        this.g = g;
        this.masa = masa;
        this.fUnu = fUnu;
        this.fDoi = fDoi;
    }

    public void run() {
        String numeFilosof = getName();
        int numarFilosof = (int)numeFilosof.charAt(10) - '0';
        while (true) {
            masa.cereFurculite(fUnu, fDoi);
            g.clearRect(15, 60 * (numarFilosof - 1) + 10, 800, 70);
            g.drawString(numeFilosof + " mananca!", 10, 60 * numarFilosof);
            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException e) {
                System.err.println("Exceptie la filosoful " + numeFilosof);
            }
            masa.elibereazaFurculite(fUnu, fDoi);
            g.clearRect(15, 60 * (numarFilosof - 1) + 10, 800, 70);
            g.drawString(numeFilosof + " gandeste!", 10, 60 * numarFilosof);
        }
    }
}
