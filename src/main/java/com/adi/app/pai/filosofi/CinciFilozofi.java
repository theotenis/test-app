package com.adi.app.pai.filosofi;


import java.applet.Applet;
import java.awt.*;

public class CinciFilozofi extends Applet {
    public void init() {
        Font f = new  Font("Courier", Font.BOLD, 50);
        setFont(f);
        setSize(400, 350);
        Graphics g = getGraphics();

        Furculite masa = new Furculite();
        Thread filosof[] = new Thread[5];
        for(int i = 0; i < filosof.length; i++) {
            filosof[i] = new Filosof("Filosoful " + (i + 1), g, masa, i, (i + 1) % filosof.length);
        }
        for(Thread fil : filosof) {
            fil.start();
        }
    }
}
