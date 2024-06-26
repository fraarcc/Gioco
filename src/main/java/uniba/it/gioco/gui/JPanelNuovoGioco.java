package uniba.it.gioco.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.database.InitDatabase;

public class JPanelNuovoGioco extends javax.swing.JPanel {

    private JFrameMain jframeMain;
    private GameModel gameModel;

    public JPanelNuovoGioco(JFrameMain jframeMain, GameModel gameModel) {
        this.jframeMain = jframeMain;
        this.gameModel = gameModel;
        initComponents();
        init();
    }

    private void init() {
        ImageIcon imageIcon = new ImageIcon("res/sfondomenu2.png");
        jLabel1.setIcon(imageIcon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        conferma = new javax.swing.JButton();
        indietro = new javax.swing.JButton();
        jNickname = new javax.swing.JTextField();
        nicknameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        conferma.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        conferma.setText("Conferma");
        conferma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confermaActionPerformed(evt);
            }
        });
        add(conferma, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 373, 165, 71));

        indietro.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        indietro.setText("Torna indietro");
        indietro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indietroActionPerformed(evt);
            }
        });
        add(indietro, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 373, -1, 72));

        jNickname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNicknameActionPerformed(evt);
            }
        });
        jNickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jNicknameKeyPressed(evt);
            }
        });
        add(jNickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 197, 374, 51));

        nicknameLabel.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        nicknameLabel.setText("Inserire il nickname");
        add(nicknameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 162, 374, 29));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void indietroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indietroActionPerformed
        // TODO add your handling code here:
        jframeMain.showCard("mainMenu");
    }//GEN-LAST:event_indietroActionPerformed

    private void confermaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confermaActionPerformed

        String nickname = jNickname.getText();
        if (nickname.length() > 0 && nickname != null) {
            if (!InitDatabase.verificaGiocatore(nickname)) {

                GameModel newGameModel = gameModel.inizializzaGioco(nickname);
                if (newGameModel != null) {
                    try {
                        gameModel = newGameModel;
                        jframeMain.updateCards(gameModel);
                        jframeMain.showCard("inGame");
                    } catch (IOException ex) {
                        Logger.getLogger(JPanelNuovoGioco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(jframeMain, "Impossibile inizializzare il gioco!", "Avviso", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(jframeMain, "Questo nickname non e' disponibile", "Avviso", JOptionPane.WARNING_MESSAGE);
                jNickname.setText("");
                jNickname.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(jframeMain, "Inserire un nickname", "Avviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_confermaActionPerformed

    private void jNicknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNicknameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jNicknameActionPerformed

    private void jNicknameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNicknameKeyPressed
        // TODO add your handling code here:    
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String nickname = jNickname.getText();
            if (nickname.length() > 0 && nickname != null) {
                if (!InitDatabase.verificaGiocatore(nickname)) {
                    GameModel newGameModel = gameModel.inizializzaGioco(nickname);
                    if (newGameModel != null) {
                        try {
                            gameModel = newGameModel;
                            jframeMain.updateCards(gameModel);
                            jframeMain.showCard("inGame");
                        } catch (IOException ex) {
                            Logger.getLogger(JPanelNuovoGioco.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(jframeMain, "Impossibile inizializzare il gioco!", "Avviso", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(jframeMain, "Questo nickname non e' disponibile", "Avviso", JOptionPane.WARNING_MESSAGE);
                    jNickname.setText("");
                    jNickname.requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(jframeMain, "Inserire un nickname", "Avviso", JOptionPane.WARNING_MESSAGE);
            }

    }//GEN-LAST:event_jNicknameKeyPressed
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conferma;
    private javax.swing.JButton indietro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jNickname;
    private javax.swing.JLabel nicknameLabel;
    // End of variables declaration//GEN-END:variables
}
