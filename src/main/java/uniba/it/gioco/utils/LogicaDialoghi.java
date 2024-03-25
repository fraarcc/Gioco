/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Npc;
import uniba.it.gioco.tipi.Stanza;


/**
 *
 * @author Nikita
 */
public class LogicaDialoghi {

    private JTextField jInputTestoArea;
    private JTextArea jOutputTestoArea;
    private JButton jBottoneInvio;
    private Giocatore giocatore;
    private List<Stanza> stanze;
    private LogicaComandi logicaComandi;
    private ActionListener inputListener; // Variabile di istanza per l'ActionListener aggiunto al bottone di invio
    private KeyListener[] keyListeners;
    private ActionListener[] actionListeners;

    public LogicaDialoghi(JTextField jInputTestoArea, JTextArea jOutputTestoArea, JButton jBottoneInvio, Giocatore giocatore, List<Stanza> stanze) {
        this.jInputTestoArea = jInputTestoArea;
        this.jOutputTestoArea = jOutputTestoArea;
        this.jBottoneInvio = jBottoneInvio;
        this.giocatore = giocatore;
        this.stanze = stanze;
    }

    public void setLogicaComandi(LogicaComandi logicaComandi) {
        this.logicaComandi = logicaComandi;
    }

    public void gestioneGuardie(Npc guardie, JTextField jInputTestoArea, JTextArea jOutputTestoArea) {
        List<String> dialoghiGuardie = guardie.getDialoghi();
        jOutputTestoArea.append(dialoghiGuardie.get(0) + "\n");
        jInputTestoArea.setText("");

        // Salva i listener attivi prima della modifica
        keyListeners = jInputTestoArea.getKeyListeners();
        actionListeners = jBottoneInvio.getActionListeners();

        // Rimuovi tutti i listener attivi
        for (KeyListener listener : keyListeners) {
            jInputTestoArea.removeKeyListener(listener);
        }
        for (ActionListener listener : actionListeners) {
            jBottoneInvio.removeActionListener(listener);
        }

        // Metodo per gestire l'input dalla text field e il click sul bottone
        inputListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestisciInput(guardie, jInputTestoArea, jOutputTestoArea);
            }
        };

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    gestisciInput(guardie, jInputTestoArea, jOutputTestoArea);
                }
            }
        };

        // Aggiungi il KeyListener per l'input dalla text field
        jInputTestoArea.addKeyListener(keyAdapter);
        // Aggiungi il listener per il bottone
        jBottoneInvio.addActionListener(inputListener);
    }

    // Metodo per gestire l'input dalla text field e il click sul bottone
    private void gestisciInput(Npc guardie, JTextField jInputTestoArea, JTextArea jOutputTestoArea) {
        List<String> dialoghiGuardie = guardie.getDialoghi();
        String inputUtente = jInputTestoArea.getText().trim().toLowerCase();
        jInputTestoArea.setText("");
        if (inputUtente.equalsIgnoreCase("si")) {
            jOutputTestoArea.append(guardie.getIndovinello().getDomanda() + "\n");
            rimuoviListener();
          
            // Metodo per gestire la risposta all'indovinello
            KeyAdapter rispostaAdapter = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER || jBottoneInvio.getModel().isPressed()) {
                        String nuovaRisposta = jInputTestoArea.getText().trim().toLowerCase();
                        jInputTestoArea.setText("");

                        if (guardie.getIndovinello().controllaRisposta(nuovaRisposta)) {
                            jOutputTestoArea.append(dialoghiGuardie.get(2) + "\n");
                            stanze.get(1).setAperto(true);
                            stanze.get(0).getNpc().setVisitato(true);
                            dialoghiGuardie.get(2);
                            rimuoviListener();
                            ripristinaListener();
                            
                        } else {
                            jOutputTestoArea.append(dialoghiGuardie.get(1) + "\n");
                            rimuoviListener();
                            ripristinaListener();
                        }
                        rimuoviListener();
                        ripristinaListener();
                    }
                }
            };

             jInputTestoArea.addKeyListener(rispostaAdapter);
        } else {
            jOutputTestoArea.append("La risposta è sbagliata, continua ad esplorare \n");
            rimuoviListener();
            ripristinaListener();
        }
    }

    // Metodo per ripristinare i listener
    private void ripristinaListener() {
        for (KeyListener listener : keyListeners) {
            jInputTestoArea.addKeyListener(listener);
        }
        for (ActionListener listener : actionListeners) {
            jBottoneInvio.addActionListener(listener);
        }
    }

    public void rimuoviListener() {
        // Rimuovi tutti i KeyListener dalla JTextField
        for (KeyListener listener : jInputTestoArea.getKeyListeners()) {
            jInputTestoArea.removeKeyListener(listener);
        }

        // Rimuovi tutti gli ActionListener dal JButton
        for (ActionListener listener : jBottoneInvio.getActionListeners()) {
            jBottoneInvio.removeActionListener(listener);
        }
    }
}
