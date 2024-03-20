/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uniba.it.gioco.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import uniba.it.gioco.GameModel;

/**
 *
 * @author Nikita
 */
public class JPanelMenu extends javax.swing.JPanel {
    private JFrameMain jframeMain;
    private GameModel gameModel;
    
    

    public JPanelMenu(JFrameMain jframeMain,GameModel gameModel){
        this.gameModel = gameModel;
        this.jframeMain = jframeMain;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nuovaPartita = new javax.swing.JButton();
        esci = new javax.swing.JButton();
        caricaPartita1 = new javax.swing.JButton();

        nuovaPartita.setFont(new java.awt.Font("NSimSun", 1, 18)); // NOI18N
        nuovaPartita.setText("Nuova Partita");
        nuovaPartita.setFocusPainted(false);
        nuovaPartita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovaPartitaActionPerformed(evt);
            }
        });

        esci.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        esci.setText("Esci");
        esci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esciActionPerformed(evt);
            }
        });

        caricaPartita1.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        caricaPartita1.setText("Carica Partita");
        caricaPartita1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaPartita1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuovaPartita, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(esci, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caricaPartita1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(nuovaPartita, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(caricaPartita1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(esci, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
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
    private javax.swing.JButton nuovaPartita;
    // End of variables declaration//GEN-END:variables
}
