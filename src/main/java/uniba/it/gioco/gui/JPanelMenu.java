package uniba.it.gioco.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import uniba.it.gioco.GameModel;

public class JPanelMenu extends javax.swing.JPanel {

    private JFrameMain jframeMain;

    public JPanelMenu(JFrameMain jframeMain, GameModel gameModel) {
        this.jframeMain = jframeMain;
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

        nuovaPartita = new javax.swing.JButton();
        esci = new javax.swing.JButton();
        caricaPartita1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nuovaPartita.setFont(new java.awt.Font("NSimSun", 1, 18)); // NOI18N
        nuovaPartita.setText("Nuova Partita");
        nuovaPartita.setFocusPainted(false);
        nuovaPartita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovaPartitaActionPerformed(evt);
            }
        });
        add(nuovaPartita, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 175, 185, 72));

        esci.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        esci.setText("Esci");
        esci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esciActionPerformed(evt);
            }
        });
        add(esci, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 383, 185, 72));

        caricaPartita1.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        caricaPartita1.setText("Carica Partita");
        caricaPartita1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaPartita1ActionPerformed(evt);
            }
        });
        add(caricaPartita1, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 280, 185, 72));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void nuovaPartitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuovaPartitaActionPerformed
        // TODO add your handling code here:
        jframeMain.showCard("newGame");
    }//GEN-LAST:event_nuovaPartitaActionPerformed

    private void esciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esciActionPerformed
        // TODO add your handling code here:
        JFrame exitFrame = new JFrame("Interrompi Esecuzione");
        exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int choice = JOptionPane.showConfirmDialog(exitFrame, "Sei sicuro di voler interrompere l'esecuzione?", "Conferma", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_esciActionPerformed

    private void caricaPartita1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caricaPartita1ActionPerformed
        jframeMain.showCard("showGames");
    }//GEN-LAST:event_caricaPartita1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton caricaPartita1;
    private javax.swing.JButton esci;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton nuovaPartita;
    // End of variables declaration//GEN-END:variables
}
