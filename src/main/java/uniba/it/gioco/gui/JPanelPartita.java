package uniba.it.gioco.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.utils.Init;
import uniba.it.gioco.utils.Input;
import uniba.it.gioco.utils.Output;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

public class JPanelPartita extends javax.swing.JPanel {

    private List<Stanza> stanze;
    private Giocatore giocatore;
    private JFrameMain jframeMain;
    private GameModel gameModel;
    private Input gestoreInput;
    private Init init;

    public JPanelPartita(JFrameMain jframeMain, GameModel gameModel) {
        this.jframeMain = jframeMain;
        this.gameModel = gameModel;
        initComponents();
        initInfoInGame();
    }

    private void initInfoInGame() {
        try {
            init = gameModel.getInit();
            stanze = gameModel.getStanze();
            giocatore = gameModel.getGiocatore();
            Output gestoreOutput = new Output(jAreaTesto, giocatore, jContainerImmagini);
            gestoreOutput.start();
            gestoreInput = new Input(this, jInputTestoArea, jInvioButtone, giocatore,
                    init, jAreaTesto, gestoreOutput, stanze, gameModel);

            if (giocatore != null) {
                String nickname = giocatore.getNickname();
                SwingUtilities.invokeLater(() -> {
                    jAreaTesto.setText("Nickname: " + nickname + "\n\n\n");
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        jInputTestoArea.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jAreaTesto = new javax.swing.JTextArea();
        jInvioButtone = new javax.swing.JButton();
        jIndietroButton = new javax.swing.JButton();
        jInputTestoArea = new javax.swing.JTextField();
        jContainerImmagini = new javax.swing.JLabel();
        jCancellaButton1 = new javax.swing.JButton();

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

        jIndietroButton.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jIndietroButton.setText("Indietro");
        jIndietroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIndietroButtonActionPerformed(evt);
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

        jCancellaButton1.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jCancellaButton1.setText("Cancella");
        jCancellaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancellaButton1ActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jIndietroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCancellaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jCancellaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIndietroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jInputTestoAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInputTestoAreaActionPerformed
        // TODO add your handling code here:      
    }//GEN-LAST:event_jInputTestoAreaActionPerformed

    private void jIndietroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIndietroButtonActionPerformed
        JFrame confermaFrame = new JFrame();
        int choice = JOptionPane.showConfirmDialog(confermaFrame, """
                                                                  Sei sicuro di voler tornare indietro?
                                                                  Ricordati di salvare""", "Attenzione", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                // TODO add your handling code here:
                jframeMain.updateCards(gameModel);
                jframeMain.showCard("mainMenu");
            } catch (IOException ex) {
                Logger.getLogger(JPanelPartita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jIndietroButtonActionPerformed

    private void jInvioButtoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvioButtoneActionPerformed
        String input = jInputTestoArea.getText();
        if (input != null && input.length() > 0) {
            gestoreInput.inviaComando(input, jInputTestoArea);
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
                gestoreInput.inviaComando(input, jInputTestoArea);
                jInputTestoArea.setText("");
            } else {
                jAreaTesto.append("Inserisci qualcosa \n");
                jInputTestoArea.setText("");
                jInputTestoArea.requestFocus();
            }
        }
    }//GEN-LAST:event_jInputTestoAreaKeyPressed

    public void disattivaBottoni() {
        jInvioButtone.setEnabled(false);
        jCancellaButton1.setEnabled(false);
        jInputTestoArea.setEnabled(false);
        jIndietroButton.requestFocus();
    }

    private void jCancellaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancellaButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCancellaButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea jAreaTesto;
    private javax.swing.JButton jCancellaButton1;
    private javax.swing.JLabel jContainerImmagini;
    private javax.swing.JButton jIndietroButton;
    private javax.swing.JTextField jInputTestoArea;
    private javax.swing.JButton jInvioButtone;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
