package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("LocalHost",3000);
        System.out.println("il client si è collegato");

        Scanner in = new Scanner(new InputStreamReader(System.in));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader rispostaServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
    
       String n1 = "";
       String n2 = "";
       String scelta = "";

       do{
        System.out.println("--------------menù---------------");
        System.out.println("premi 1 per fare la somma di 2 numeri");
        System.out.println("premi 2 per la sottrazione");
        System.out.println("premi 3 per la moltiplicazione");
        System.out.println("premi 4 per la divisione");
        System.out.println("premi '!' per terminare le operazioni \n");
        System.out.println("compiere una scelta: ");
        scelta = in.nextLine();
        if(scelta.equals("!")){
            break;
        }
        out.writeBytes(scelta + "\n");
        System.out.println("scrivere i 2 numeri");
        n1 = in.nextLine();
        out.writeBytes(n1 + "\n");
        n2 = in.nextLine();
        out.writeBytes(n2 + "\n");

        String risultatoOperazione = rispostaServer.readLine();
        System.out.println("Risposta del server: " + risultatoOperazione + "\n");

       }while(!scelta.equals("!"));

       s.close();
       in.close();
       System.out.println("fine connessione");
    }
}