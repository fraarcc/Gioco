/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uniba.it.gioco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.storia.Init;
import uniba.it.gioco.storia.Input;
import uniba.it.gioco.storia.Output;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author 39379
 */
public class JPanelPartita extends javax.swing.JPanel {

    private List<Stanza> stanze;
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
        
        try {
            init = gameModel.getInit();
            stanze = gameModel.getStanze();
            giocatore = gameModel.getGiocatore();
            if (giocatore != null) {
                String nickname = giocatore.getNickname();
                SwingUtilities.invokeLater(() -> {
                    jAreaTesto.setText("Il nome del giocatore è: " + nickname + "\n\n\n");
                });

                Output output = new Output(jAreaTesto, giocatore, jContainerImmagini);
                output.start();

                inputThread = new Input(jInputTestoArea, jInvioButtone,giocatore, init, jAreaTesto,  output, stanze, gameModel);
                //inputThread.start();
            } else {
                System.out.println("Giocatore non inizializzato");
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        jInputTestoArea.requestFocus();
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
        jAreaTesto = new javax.swing.JTextArea();
        jInvioButtone = new javax.swing.JButton();
        jCancellaButton = new javax.swing.JButton();
        jInputTestoArea = new javax.swing.JTextField();
        jContainerImmagini = new javax.swing.JLabel();

        setForeground(new java.awt.Color(0, 0, 0));

        jAreaTesto.setEditable(false);
        jAreaTesto.setColumns(20);
        jAreaTesto.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jAreaTesto.setForeground(new java.awt.Color(204, 204, 204));
        jAreaTesto.setRows(5);
        jScrollPane1.setViewportView(jAreaTesto);

        jInvioButtone.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jInvioButtone.setText("Conferma");
        jInvioButtone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvioButtoneActionPerformed(evt);
            }
        });

        jCancellaButton.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jCancellaButton.setText("Cancella");
        jCancellaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancellaButtonActionPerformed(evt);
            }
        });

        jInputTestoArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInputTestoAreaActionPerformed(evt);
            }
        });
        jInputTestoArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jInputTestoAreaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jContainerImmagini, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 287, Short.MAX_VALUE)
                        .addComponent(jCancellaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInvioButtone, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jInputTestoArea))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jInputTestoArea, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jContainerImmagini, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jInvioButtone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCancellaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jInputTestoAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInputTestoAreaActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jInputTestoAreaActionPerformed

    private void jCancellaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancellaButtonActionPerformed
        // TODO add your handling code here:
        jInputTestoArea.setText("");
    }//GEN-LAST:event_jCancellaButtonActionPerformed

    private void jInvioButtoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvioButtoneActionPerformed
        String input = jInputTestoArea.getText();
        if (input != null && input.length() > 0) {
            inputThread.inviaComando(input, jInputTestoArea);
            jInputTestoArea.setText("");
        } else {
            jAreaTesto.append("Inserisci qualcosa \n");
            jInputTestoArea.setText("");
            jInputTestoArea.requestFocus();
        }
    }//GEN-LAST:event_jInvioButtoneActionPerformed

    private void jInputTestoAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jInputTestoAreaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String input = jInputTestoArea.getText();
            if (input != null && input.length() > 0) {
                inputThread.inviaComando(input, jInputTestoArea);
                jInputTestoArea.setText("");
            } else {
                jAreaTesto.append("Inserisci qualcosa \n");
                jInputTestoArea.setText("");
                jInputTestoArea.requestFocus();
            }
        }
    }//GEN-LAST:event_jInputTestoAreaKeyPressed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea jAreaTesto;
    private javax.swing.JButton jCancellaButton;
    private javax.swing.JLabel jContainerImmagini;
    private javax.swing.JTextField jInputTestoArea;
    private javax.swing.JButton jInvioButtone;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
