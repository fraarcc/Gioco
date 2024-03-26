/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.utils;

import java.awt.Image;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class Output extends Thread {

    private JTextArea storiaTextArea;
    private Giocatore giocatoreCorrente;
    private JLabel containerImmagini;
    private Map<String, String> storiaMap;
    private boolean stanzaCambiata;

    public Output(JTextArea storiaTextArea, Giocatore giocatoreCorrente, JLabel containerImmagini) {
        this.storiaTextArea = storiaTextArea;
        this.giocatoreCorrente = giocatoreCorrente;
        this.containerImmagini = containerImmagini;
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
        impostaImmagineStanzaCorrente();
    }

    private String getDescrizioneCompletaStanzaCorrente() {
        Stanza stanzaCorrente = giocatoreCorrente.getStanzaCorrente();
        if (stanzaCorrente != null) {
            StringBuilder descrizioneCompleta = new StringBuilder();
            descrizioneCompleta.append("Ti trovi in: ").append(stanzaCorrente.getNome()).append("\n");
            String dialogo = storiaMap.get(stanzaCorrente.getNome());
            if (dialogo != null) {
                descrizioneCompleta.append(dialogo);
            }
            return descrizioneCompleta.toString();
        }
        return "Descrizione stanza non disponibile";
    }

private void impostaImmagineStanzaCorrente() {
    Stanza stanzaCorrente = giocatoreCorrente.getStanzaCorrente();
    if (stanzaCorrente != null) {
        String imageName = stanzaCorrente.getNome();
        String imagePathPNG = "./res/" + imageName + ".png";
        String imagePathGIF = "./res/" + imageName + ".gif";
        
        ImageIcon imageIcon = null;
        if (new File(imagePathPNG).exists()) {
            imageIcon = new ImageIcon(imagePathPNG);
        } else if (new File(imagePathGIF).exists()) {
            imageIcon = new ImageIcon(imagePathGIF);
        }
        
        if (imageIcon != null) {
            containerImmagini.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(410, 500, Image.SCALE_DEFAULT)));
        } else {
      
            containerImmagini.setIcon(null);
        }
    } else {
 
        containerImmagini.setIcon(null);
    }
}


    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                if (stanzaCambiata) {
                    aggiornaStoria();
                    stanzaCambiata = false;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void aspettaCambioStanza() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void cambioStanza() {
        stanzaCambiata = true;
        notify();
    }

    public void stopThread() {
        stanzaCambiata = false;
    }

    public void direzioneErrataMsg() {
        storiaTextArea.append("Non puoi andare in questa direzione" + "\n");
    }

    public void stanzaChiusaMsg() {
        storiaTextArea.append("La stanza e' chiusa " + "\n");
    }
}
