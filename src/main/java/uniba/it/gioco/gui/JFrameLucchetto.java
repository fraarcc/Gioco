package uniba.it.gioco.gui;

import java.util.function.Consumer;
import javax.swing.ImageIcon;

public class JFrameLucchetto extends javax.swing.JFrame {

    private boolean statoGioco;
    private Consumer<Boolean> callback;

    public JFrameLucchetto(Consumer<Boolean> callback) {
        this.callback = callback;
        initComponents();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrameLucchetto.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("res/lucchetto.png");
        jLabel1.setIcon(imageIcon);
        jLabel1.setVisible(true);
    }

    public boolean getStatoGioco() {
        return statoGioco;
    }

    private boolean sblocca() {
        statoGioco = (int) jSpinner1.getValue() == 1 && (int) jSpinner2.getValue() == 2 && (int) jSpinner3.getValue() == 3;
        return statoGioco;
    }

    public boolean isStatoGioco() {
        return statoGioco;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jButtonSblocca = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSpinner1.setFont(new java.awt.Font("SimSun", 0, 36)); // NOI18N
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 90, 120));

        jSpinner2.setFont(new java.awt.Font("SimSun", 0, 36)); // NOI18N
        getContentPane().add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 90, 120));

        jSpinner3.setFont(new java.awt.Font("SimSun", 0, 36)); // NOI18N
        getContentPane().add(jSpinner3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 90, 120));

        jButtonSblocca.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jButtonSblocca.setText("Sblocca");
        jButtonSblocca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSbloccaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSblocca, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, 42));

        jLabel1.setIcon(new javax.swing.ImageIcon("E:\\Gioco\\res\\lucchetto.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 380, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSbloccaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSbloccaActionPerformed
        statoGioco = sblocca();
        if (callback != null) {
            callback.accept(statoGioco);
        }
        dispose();
    }//GEN-LAST:event_jButtonSbloccaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSblocca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    // End of variables declaration//GEN-END:variables
}
