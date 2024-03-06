/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uniba.it.gioco.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikita
 */
public class JPanelMenu extends javax.swing.JPanel {
    private JFrameMain jframeMain;
    
    
    

    public JPanelMenu(JFrameMain jframeMain){
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

        nuovaPartita.setText("Nuova Partita");
        nuovaPartita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovaPartitaActionPerformed(evt);
            }
        });

        esci.setText("Esci");
        esci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esciActionPerformed(evt);
            }
        });

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
                .addContainerGap(310, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(esci, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caricaPartita1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuovaPartita, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(305, 305, 305))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(nuovaPartita, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(caricaPartita1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(esci, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_caricaPartita1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton caricaPartita1;
    private javax.swing.JButton esci;
    private javax.swing.JButton nuovaPartita;
    // End of variables declaration//GEN-END:variables
}