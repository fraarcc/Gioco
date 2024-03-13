/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uniba.it.gioco.gui;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.storia.Init;
import uniba.it.gioco.storia.Input;
import uniba.it.gioco.storia.Output;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Giocatore;

/**
 *
 * @author 39379
 */
public class JPanelPartita extends javax.swing.JPanel {

    private Giocatore giocatore;
    private JFrameMain jframeMain;
    private GameModel gameModel;
    private Input inputThread;
    private Init init;

    /**
     * Creates new form JPanelPartita
     */
    public JPanelPartita(JFrameMain jframeMain, GameModel gameModel) {
        this.jframeMain = jframeMain;
        this.gameModel = gameModel;
        initComponents();
        init = gameModel.getInit();
        // Ottieni il giocatore
        giocatore = gameModel.getGiocatore();
        if (giocatore != null) {
            String nickname = giocatore.getNickname();
            // Aggiorna l'interfaccia utente con il nome del giocatore
            SwingUtilities.invokeLater(() -> {
                areaTesto.setText("Il nome del giocatore è: " + nickname + "\n\n\n");
            });

            // Crea un'istanza di StampaStoria
            Output output = new Output(areaTesto, giocatore);

            // Aggiornamento della storia quando il giocatore si sposta in una nuova stanza
            output.start();

            inputThread = new Input(inputTesto, giocatore, init, areaTesto, output);
            inputThread.start();
        } else {
            System.out.println("Giocatore non inizializzato");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaTesto = new javax.swing.JTextArea();
        invioButton = new javax.swing.JButton();
        cancellaButton = new javax.swing.JButton();
        inputTesto = new javax.swing.JTextField();

        areaTesto.setEditable(false);
        areaTesto.setColumns(20);
        areaTesto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        areaTesto.setRows(5);
        jScrollPane1.setViewportView(areaTesto);

        invioButton.setText("Conferma");
        invioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invioButtonActionPerformed(evt);
            }
        });

        cancellaButton.setText("Cancella");
        cancellaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaButtonActionPerformed(evt);
            }
        });

        inputTesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTestoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancellaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invioButton)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                            .addComponent(inputTesto))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inputTesto, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invioButton)
                    .addComponent(cancellaButton))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inputTestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTestoActionPerformed
        // TODO add your handling code here:
        inputThread.inviaComando(inputTesto.getText().trim());
        inputTesto.setText("");
    }//GEN-LAST:event_inputTestoActionPerformed

    private void cancellaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaButtonActionPerformed
        // TODO add your handling code here:
        inputTesto.setText("");
    }//GEN-LAST:event_cancellaButtonActionPerformed

    private void invioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invioButtonActionPerformed
        inputThread.inviaComando(inputTesto.getText().trim());
        inputTesto.setText("");
    }//GEN-LAST:event_invioButtonActionPerformed

    public void chiudiInputThread() {
        inputThread.interrupt();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea areaTesto;
    private javax.swing.JButton cancellaButton;
    private javax.swing.JTextField inputTesto;
    private javax.swing.JButton invioButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
