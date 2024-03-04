/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikita
 */
public class StampaStoria  extends Thread {
    private JTextArea textArea;
    
    
    public StampaStoria(JTextArea textArea){
        this.textArea = textArea;
    }
    
    @Override
    public void run(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("storia.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StampaStoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        
        try {
            while ((line = reader.readLine()) != null) {
                try {
                    aggiornaTextArea(line);
                    
                    // Aggiungi un ritardo per simulare la lettura della storia
                    Thread.sleep(1000); // Puoi regolare il ritardo a tuo piacimento
                } catch (InterruptedException ex) {
                    Logger.getLogger(StampaStoria.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   } catch (IOException ex) {
            Logger.getLogger(StampaStoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void aggiornaTextArea(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text + "\n"));
    }     
}
