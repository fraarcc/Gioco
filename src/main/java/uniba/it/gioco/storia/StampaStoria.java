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
public class StampaStoria extends Thread {
    private JTextArea storiaTextArea;
    private Giocatore giocatoreCorrente;
    private Map<String, String> storiaMap;

    public StampaStoria(JTextArea storiaTextArea, Giocatore giocatoreCorrente) {
        this.storiaTextArea = storiaTextArea;
        this.giocatoreCorrente = giocatoreCorrente;
        this.storiaMap = new HashMap<>();
        caricaStoriaDaFile();
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

    private String getDescrizioneStanzaCorrente() {
        Stanza stanzaCorrente = giocatoreCorrente.getStanzaCorrente();
        if (stanzaCorrente != null) {
            StringBuilder descrizione = new StringBuilder();

            descrizione.append("Stanza: ").append(stanzaCorrente.getNome()).append("\n");
            descrizione.append("Descrizione: ").append(stanzaCorrente.getDescrizione()).append("\n");
            //descrizione.append("Oggetti presenti stanza: ").append(stanzaCorrente.getOggettiPresentiStanza()).append("/n");
            return descrizione.toString();
        }
        return "Descrizione stanza non disponibile";
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
        while (true) {
            String descrizioneCompleta = getDescrizioneCompletaStanzaCorrente();
            storiaTextArea.setText(descrizioneCompleta);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StampaStoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
