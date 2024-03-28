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

public class LogicaDialoghi {

    private JTextField jInputTestoArea;
    private JButton jBottoneInvio;
    private List<Stanza> stanze;
    private ActionListener inputListener;
    private KeyListener[] keyListeners;
    private ActionListener[] actionListeners;

    public LogicaDialoghi(JTextField jInputTestoArea, JTextArea jOutputTestoArea, JButton jBottoneInvio, Giocatore giocatore, List<Stanza> stanze) {
        this.jInputTestoArea = jInputTestoArea;
        this.jBottoneInvio = jBottoneInvio;
        this.stanze = stanze;
    }

    public void setLogicaComandi(LogicaComandi logicaComandi) {
    }

    public void gestioneGuardie(Npc guardie, JTextField jInputTestoArea, JTextArea jOutputTestoArea) {
        List<String> dialoghiGuardie = guardie.getDialoghi();
        jOutputTestoArea.append(dialoghiGuardie.get(0) + "\n");
        jInputTestoArea.setText("");

        keyListeners = jInputTestoArea.getKeyListeners();
        actionListeners = jBottoneInvio.getActionListeners();

        for (KeyListener listener : keyListeners) {
            jInputTestoArea.removeKeyListener(listener);
        }
        for (ActionListener listener : actionListeners) {
            jBottoneInvio.removeActionListener(listener);
        }

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

        jInputTestoArea.addKeyListener(keyAdapter);

        jBottoneInvio.addActionListener(inputListener);
    }

    private void gestisciInput(Npc guardie, JTextField jInputTestoArea, JTextArea jOutputTestoArea) {
        List<String> dialoghiGuardie = guardie.getDialoghi();
        String inputUtente = jInputTestoArea.getText().trim().toLowerCase();
        jInputTestoArea.setText("");
        if (inputUtente.equalsIgnoreCase("si")) {
            jOutputTestoArea.append(guardie.getIndovinello().getDomanda() + "\n");
            rimuoviListener();

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

    private void ripristinaListener() {
        for (KeyListener listener : keyListeners) {
            jInputTestoArea.addKeyListener(listener);
        }
        for (ActionListener listener : actionListeners) {
            jBottoneInvio.addActionListener(listener);
        }
    }

    public void rimuoviListener() {

        for (KeyListener listener : jInputTestoArea.getKeyListeners()) {
            jInputTestoArea.removeKeyListener(listener);
        }

        for (ActionListener listener : jBottoneInvio.getActionListeners()) {
            jBottoneInvio.removeActionListener(listener);
        }
    }
}
