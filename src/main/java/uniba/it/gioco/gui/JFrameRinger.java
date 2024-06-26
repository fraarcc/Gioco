package uniba.it.gioco.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import javax.swing.ImageIcon;

public class JFrameRinger extends javax.swing.JFrame {

    private boolean esito = false;
    private final ArrayList<Integer> sequenza = new ArrayList<>();
    private Consumer<Boolean> callback;

    public JFrameRinger(Consumer<Boolean> callback) {
        this.callback = callback;
        initComponents();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrameLucchetto.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("res/beckerGrande.png");
        jLabelBecker0.setIcon(imageIcon);
        ImageIcon imageIcon2 = new ImageIcon("res/beckerGrande1.png");
        jLabelBecker1.setIcon(imageIcon2);
        ImageIcon imageIcon3 = new ImageIcon("res/beckerGrande2.png");
        jLabelBecker2.setIcon(imageIcon3);
        ImageIcon imageIcon4 = new ImageIcon("res/beckerGrande3.png");
        jLabelBecker3.setIcon(imageIcon4);
        ImageIcon imageIcon5 = new ImageIcon("res/beckerGrande4.png");
        jLabelBecker4.setIcon(imageIcon5);
        ImageIcon imageIcon6 = new ImageIcon("res/beckerEsplodeGrande.png");
        jLabelBeckerExp.setIcon(imageIcon6);
        ImageIcon imageIcon7 = new ImageIcon("res/sodioCloruro.png");
        jButton1.setIcon(imageIcon7);
        ImageIcon imageIcon8 = new ImageIcon("res/potassioCloruro.png");
        jButton2.setIcon(imageIcon8);
        ImageIcon imageIcon9 = new ImageIcon("res/calcioCloruroDii.png");
        jButton3.setIcon(imageIcon9);
        ImageIcon imageIcon10 = new ImageIcon("res/sodioAcetato.png");
        jButton4.setIcon(imageIcon10);

    }

    private void mostraBecker1() {

        jPanBecker0.setVisible(false);
        jPanBecker1.setVisible(true);
        jPanBecker2.setVisible(false);
        jPanBecker3.setVisible(false);
        jPanBecker4.setVisible(false);
        jPanBeckerExp.setVisible(false);

        jLabel1.setText("Inserisci i composti nell'ordine corretto");
    }

    private void mostraBecker2() {

        jPanBecker0.setVisible(false);
        jPanBecker1.setVisible(false);
        jPanBecker2.setVisible(true);
        jPanBecker3.setVisible(false);
        jPanBecker4.setVisible(false);
        jPanBeckerExp.setVisible(false);

        jLabel1.setText("Inserisci i composti nell'ordine corretto");
    }

    private void mostraBecker3() {

        jPanBecker0.setVisible(false);
        jPanBecker1.setVisible(false);
        jPanBecker2.setVisible(false);
        jPanBecker3.setVisible(true);
        jPanBecker4.setVisible(false);
        jPanBeckerExp.setVisible(false);

        jLabel1.setText("Inserisci i composti nell'ordine corretto");
    }

    private void mostraBecker4() {

        jPanBecker0.setVisible(false);
        jPanBecker1.setVisible(false);
        jPanBecker2.setVisible(false);
        jPanBecker3.setVisible(false);
        jPanBecker4.setVisible(true);
        jPanBeckerExp.setVisible(false);

        jLabel1.setText("Hai ottenuto il ringer acetato! Sembravi quasi un vero medico.");

        esito = true;
        if (callback != null) {
            callback.accept(esito);
        }
        dispose();
    }

    private void mostraBeckerExp() {

        jPanBecker0.setVisible(false);
        jPanBecker1.setVisible(false);
        jPanBecker2.setVisible(false);
        jPanBecker3.setVisible(false);
        jPanBecker4.setVisible(false);
        jPanBeckerExp.setVisible(true);

        jLabel1.setText("BOOM! Ritenta, senza uccidere nessuno, grazie.");
    }

    private void setImmagine() {

        if (sequenza.equals(Arrays.asList(1))) {

            mostraBecker1();
        } else if (sequenza.equals(Arrays.asList(1, 2))) {

            mostraBecker2();
        } else if (sequenza.equals(Arrays.asList(1, 2, 3))) {

            mostraBecker3();
        } else if (sequenza.equals(Arrays.asList(1, 2, 3, 4))) {

            mostraBecker4();
            esito = true;

        } else {

            mostraBeckerExp();
            sequenza.clear();
        }
    }

    public boolean getEsito() {
        return esito;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanBecker0 = new javax.swing.JPanel();
        jLabelBecker0 = new javax.swing.JLabel();
        jPanBecker1 = new javax.swing.JPanel();
        jLabelBecker1 = new javax.swing.JLabel();
        jPanBecker2 = new javax.swing.JPanel();
        jLabelBecker2 = new javax.swing.JLabel();
        jPanBecker3 = new javax.swing.JPanel();
        jLabelBecker3 = new javax.swing.JLabel();
        jPanBecker4 = new javax.swing.JPanel();
        jLabelBecker4 = new javax.swing.JLabel();
        jPanBeckerExp = new javax.swing.JPanel();
        jLabelBeckerExp = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanBecker0.setRequestFocusEnabled(false);

        jLabelBecker0.setText("jLabel1");

        javax.swing.GroupLayout jPanBecker0Layout = new javax.swing.GroupLayout(jPanBecker0);
        jPanBecker0.setLayout(jPanBecker0Layout);
        jPanBecker0Layout.setHorizontalGroup(
            jPanBecker0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBecker0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        jPanBecker0Layout.setVerticalGroup(
            jPanBecker0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBecker0, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );

        getContentPane().add(jPanBecker0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 370, 370));
        jPanBecker0.getAccessibleContext().setAccessibleName("");

        jLabelBecker1.setText("jLabel1");

        javax.swing.GroupLayout jPanBecker1Layout = new javax.swing.GroupLayout(jPanBecker1);
        jPanBecker1.setLayout(jPanBecker1Layout);
        jPanBecker1Layout.setHorizontalGroup(
            jPanBecker1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );
        jPanBecker1Layout.setVerticalGroup(
            jPanBecker1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );

        getContentPane().add(jPanBecker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 370, 370));

        jLabelBecker2.setText("jLabel1");

        javax.swing.GroupLayout jPanBecker2Layout = new javax.swing.GroupLayout(jPanBecker2);
        jPanBecker2.setLayout(jPanBecker2Layout);
        jPanBecker2Layout.setHorizontalGroup(
            jPanBecker2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );
        jPanBecker2Layout.setVerticalGroup(
            jPanBecker2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );

        getContentPane().add(jPanBecker2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 370, 370));

        jLabelBecker3.setText("jLabel2");

        javax.swing.GroupLayout jPanBecker3Layout = new javax.swing.GroupLayout(jPanBecker3);
        jPanBecker3.setLayout(jPanBecker3Layout);
        jPanBecker3Layout.setHorizontalGroup(
            jPanBecker3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker3, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );
        jPanBecker3Layout.setVerticalGroup(
            jPanBecker3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker3, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );

        getContentPane().add(jPanBecker3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 370, 370));

        jLabelBecker4.setText("jLabel3");

        javax.swing.GroupLayout jPanBecker4Layout = new javax.swing.GroupLayout(jPanBecker4);
        jPanBecker4.setLayout(jPanBecker4Layout);
        jPanBecker4Layout.setHorizontalGroup(
            jPanBecker4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker4, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );
        jPanBecker4Layout.setVerticalGroup(
            jPanBecker4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBecker4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBecker4, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );

        getContentPane().add(jPanBecker4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 370, 370));

        jLabelBeckerExp.setText("jLabel4");

        javax.swing.GroupLayout jPanBeckerExpLayout = new javax.swing.GroupLayout(jPanBeckerExp);
        jPanBeckerExp.setLayout(jPanBeckerExpLayout);
        jPanBeckerExpLayout.setHorizontalGroup(
            jPanBeckerExpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBeckerExpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBeckerExp, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );
        jPanBeckerExpLayout.setVerticalGroup(
            jPanBeckerExpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanBeckerExpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBeckerExp, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );

        getContentPane().add(jPanBeckerExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 370, 370));

        jButton1.setText("jButton1");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 90, 370));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 90, 370));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 90, 370));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 90, 370));

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 770, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        sequenza.add(1);
        setImmagine();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        sequenza.add(3);
        setImmagine();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        sequenza.add(4);
        setImmagine();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        sequenza.add(2);
        setImmagine();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBecker0;
    private javax.swing.JLabel jLabelBecker1;
    private javax.swing.JLabel jLabelBecker2;
    private javax.swing.JLabel jLabelBecker3;
    private javax.swing.JLabel jLabelBecker4;
    private javax.swing.JLabel jLabelBeckerExp;
    private javax.swing.JPanel jPanBecker0;
    private javax.swing.JPanel jPanBecker1;
    private javax.swing.JPanel jPanBecker2;
    private javax.swing.JPanel jPanBecker3;
    private javax.swing.JPanel jPanBecker4;
    private javax.swing.JPanel jPanBeckerExp;
    // End of variables declaration//GEN-END:variables
}
