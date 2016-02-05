package com.adi.app.pai.prodcons;


import java.io.*;

public class PipeTest {
    static public void main(String[] args) {
        BufferedReader in;
        String linie;
        int maximNumere = 0;
        try {
            in = new BufferedReader(new InputStreamReader(System.in), 1);
            System.out.flush();
            System.out.println("Dati numarul maxim de numere: ");
            linie = in.readLine();
            maximNumere = Integer.parseInt(linie);
            in.close();
        }
        catch(IOException e) {
            System.out.println("Citire gresita de la tastatura " + e.toString());
        }
        PipeTest obiect = new PipeTest(System.out, maximNumere);
    }

    private PipeTest(PrintStream iesire, int maximNumere) {
        DataInputStream fibonacci = apelFibonacci(maximNumere);
        DataInputStream prim = apelPrim(maximNumere);
        try {
            int x = fibonacci.readInt();
            int y = prim.readInt();
            while(x < maximNumere) {
                if(x == y) {
                    iesire.println("Numarul " + x + " este fibonacci si prim!");
                    x = fibonacci.readInt();
                    y = prim.readInt();
                }
                else if (x < y) {
                    x = fibonacci.readInt();
                }
                else {
                    y = prim.readInt();
                }
            }
        }
        catch(IOException e) {
            System.exit(0);
        }
    }

    private DataInputStream apelFibonacci(int maximNumere) {
        try {
            PipedInputStream intrare = new PipedInputStream();
            PipedOutputStream iesire = new PipedOutputStream(intrare);
            Thread firFibonacci = new GenereazaFibonacci(
              new DataOutputStream(iesire), maximNumere
            );
            firFibonacci.start();
            return new DataInputStream(intrare);
        }
        catch(IOException e) {
            return null;
        }
    }

    private DataInputStream apelPrim(int maximNumere) {
        try {
            PipedInputStream intrare = new PipedInputStream();
            PipedOutputStream iesire = new PipedOutputStream(intrare);
            Thread firPrim = new GenereazaPrim(new DataOutputStream(iesire), maximNumere);
            firPrim.start();
            return new DataInputStream(intrare);
        }
        catch(IOException e) {
            return null;
        }
    }
}
