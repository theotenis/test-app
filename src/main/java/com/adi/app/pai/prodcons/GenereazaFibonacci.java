package com.adi.app.pai.prodcons;

import java.io.DataOutputStream;
import java.io.IOException;

public class GenereazaFibonacci extends Thread{
    private DataOutputStream iesire;
    private int maximNumere;

    public GenereazaFibonacci(DataOutputStream iesire, int maximNumere) {
        this.iesire = iesire;
        this.maximNumere = maximNumere;
    }

    public void run() {
        int n = 0;
        int m = 1;
        try {
            iesire.writeInt(m);
            int nouaValoare;
            while(m < maximNumere) {
                nouaValoare = n + m;
                n = m;
                m = nouaValoare;
                System.out.println("Noul numar Fibonacci = " + nouaValoare);
                iesire.writeInt(nouaValoare);
            }
            iesire.close();
        }
        catch(IOException e) {
            return;
        }
    }
}
