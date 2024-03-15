/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class Output extends Thread {

    private JTextArea storiaTextArea;
    private Giocatore giocatoreCorrente;
    private Map<String, String> storiaMap;
    private boolean stanzaCambiata;

    public Output(JTextArea storiaTextArea, Giocatore giocatoreCorrente) {
        this.storiaTextArea = storiaTextArea;
        this.giocatoreCorrente = giocatoreCorrente;
        this.storiaMap = new HashMap<>();
        caricaStoriaDaFile();
        stanzaCambiata = true;
    }

    public void caricaStoriaDaFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(".\\res\\storia.txt"))) {
            String linea;
            String chiaveStanza = null;
            StringBuilder descrizioneStanza = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("#")) {
                    if (chiaveStanza != null) {
                        storiaMap.put(chiaveStanza, descrizioneStanza.toString());
                    }
                    chiaveStanza = linea.substring(1).trim();
                    descrizioneStanza = new StringBuilder();
                } else {
                    descrizioneStanza.append(linea).append("\n");
                }
            }
            if (chiaveStanza != null) {
                storiaMap.put(chiaveStanza, descrizioneStanza.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aggiornaStoria() {
    String descrizioneCompleta = getDescrizioneCompletaStanzaCorrente();
    SwingUtilities.invokeLater(() -> storiaTextArea.append(descrizioneCompleta));
}

    private String getDescrizioneCompletaStanzaCorrente() {
        Stanza stanzaCorrente = giocatoreCorrente.getStanzaCorrente();
        if (stanzaCorrente != null) {
            StringBuilder descrizioneCompleta = new StringBuilder();

            // Aggiungi la descrizione della stanza
            descrizioneCompleta.append("Stanza: ").append(stanzaCorrente.getNome()).append("\n");
            descrizioneCompleta.append("Descrizione: ").append(stanzaCorrente.getDescrizione()).append("\n\n");

            // Aggiungi il dialogo corrispondente dalla mappa storiaMap
            String dialogo = storiaMap.get(stanzaCorrente.getNome());
            if (dialogo != null) {
                descrizioneCompleta.append(dialogo);
            }

            return descrizioneCompleta.toString();
        }
        return "Descrizione stanza non disponibile";
    }

   @Override
    public void run() {
        // Stampiamo la prima stanza
       // aggiornaStoria();

        // Attendiamo un cambio di stanza solo dopo aver stampato la prima stanza
        synchronized (this) {
            while (true) {
                if (stanzaCambiata) {
                    aggiornaStoria();
                    stanzaCambiata = false;
                    try {
                        wait(); // Attendiamo fino a quando non avviene un cambio di stanza
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public synchronized void aspettaCambioStanza() {
        try {
            wait(); // Il thread attende fino a quando non riceve una notifica
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     public synchronized void cambioStanza() {
        stanzaCambiata = true;
        notify(); // Notifichiamo il thread di StampaStoria che la stanza è cambiata
    }

    public void stopThread() {
        stanzaCambiata = false;
    }
    
    public void direzioneErrataMsg(){
        storiaTextArea.append("Non puoi andare in questa direzione" + "\n");
    }
    
    public void stanzaChiusaMsg(){
        storiaTextArea.append("La stanza e' chiusa " + "\n");
    }
}
