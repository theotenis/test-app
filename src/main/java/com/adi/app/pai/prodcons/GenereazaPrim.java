package com.adi.app.pai.prodcons;

import java.io.DataOutputStream;
import java.io.IOException;

public class GenereazaPrim extends Thread {
    private DataOutputStream iesire;
    private int maximNumere;

    public GenereazaPrim(DataOutputStream iesire, int maximNumere) {
        this.iesire = iesire;
        this.maximNumere = maximNumere;
    }

    public void run() {
        int nouaValoare = 1;
        boolean estePrim;
        int i;
        try {
            while(nouaValoare < maximNumere) {
                nouaValoare++;
                estePrim = true;
                for(i = 2; i * i <= nouaValoare; i++) {
                    if(nouaValoare % i == 0) {
                        estePrim = false;
                        break;
                    }
                }
                if(estePrim) {
                    System.out.println("Noul numar prim = " + nouaValoare);
                    iesire.writeInt(nouaValoare);
                }
            }
            iesire.close();
        }
        catch(IOException e) {
            return;
        }
    }
}
