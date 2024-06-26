package uniba.it.gioco.gui;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.database.InitDatabase;
import uniba.it.gioco.utils.Init;

public class JPanelMostraPartite extends javax.swing.JPanel {

    private GameModel gameModel;
    private JFrameMain jframeMain;

    public JPanelMostraPartite(JFrameMain jframeMain, GameModel gameModel) {

        this.gameModel = gameModel;
        this.jframeMain = jframeMain;
        initComponents();
        prelievoDati();
        init();
    }

    private void init() {
        ImageIcon imageIcon = new ImageIcon("res/sfondomenu2.png");
        jLabel1.setIcon(imageIcon);
    }

    private void prelievoDati() {
        try {
            ResultSet resultSet = InitDatabase.stampaPartiteDisponibiliResultSet();
            if (!resultSet.next()) {
            } else {
                inserimentoTabella(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero delle partite disponibili.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rimuoviRigheConValoriNull(DefaultTableModel modelloTabella) {
        int rowCount = modelloTabella.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            for (int j = 0; j < modelloTabella.getColumnCount(); j++) {
                if (modelloTabella.getValueAt(i, j) == null) {
                    modelloTabella.removeRow(i);
                    break;
                }
            }
        }
    }

    private void inserimentoTabella(ResultSet resultSet) throws SQLException {
        DefaultTableModel modelloTabella = (DefaultTableModel) jTablePartite.getModel();
        rimuoviRigheConValoriNull(modelloTabella);
        resultSet.beforeFirst();
        while (resultSet.next()) {
            Integer id = (Integer) resultSet.getObject("id");
            String nickname = resultSet.getString("nickname");
            Timestamp timestamp = resultSet.getTimestamp("timestamp");
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = timestamp != null ? dataFormat.format(timestamp) : "";
            if (id != null && nickname != null && !nickname.isEmpty() && formattedDateTime != null) {
                modelloTabella.addRow(new Object[]{id, nickname, formattedDateTime});
            }
        }
    }

    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePartite = new javax.swing.JTable();
        jButtonCarica = new javax.swing.JButton();
        jButtonIndietro = new javax.swing.JButton();
        jButtonCancella1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablePartite.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jTablePartite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Partita", " Nickname", "Data Creazione"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePartite);
        if (jTablePartite.getColumnModel().getColumnCount() > 0) {
            jTablePartite.getColumnModel().getColumn(0).setResizable(false);
            jTablePartite.getColumnModel().getColumn(1).setResizable(false);
            jTablePartite.getColumnModel().getColumn(2).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 97, 645, 314));

        jButtonCarica.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButtonCarica.setText("Carica");
        jButtonCarica.setPreferredSize(new java.awt.Dimension(110, 32));
        jButtonCarica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaricaActionPerformed(evt);
            }
        });
        add(jButtonCarica, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 429, -1, -1));

        jButtonIndietro.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButtonIndietro.setText("Indietro");
        jButtonIndietro.setPreferredSize(new java.awt.Dimension(110, 32));
        jButtonIndietro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIndietroActionPerformed(evt);
            }
        });
        add(jButtonIndietro, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 429, -1, -1));

        jButtonCancella1.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButtonCancella1.setText("Cancella");
        jButtonCancella1.setPreferredSize(new java.awt.Dimension(110, 32));
        jButtonCancella1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancella1ActionPerformed(evt);
            }
        });
        add(jButtonCancella1, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 429, -1, -1));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIndietroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIndietroActionPerformed
        try {
            // TODO add your handling code here:
            jframeMain.updateCards(gameModel);
            jframeMain.showCard("mainMenu");
        } catch (IOException ex) {
            Logger.getLogger(JPanelMostraPartite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonIndietroActionPerformed

    private void jButtonCaricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaricaActionPerformed
        int rigaSelezionata = jTablePartite.getSelectedRow();
        if (rigaSelezionata != -1) {
            try {
                int idPartita = (int) jTablePartite.getModel().getValueAt(rigaSelezionata, 0);
                String nomeUtente = (String) jTablePartite.getModel().getValueAt(rigaSelezionata, 1);
                if (nomeUtente != null) {
                    Init init = gameModel.getInit();
                    gameModel = InitDatabase.caricaPartita(idPartita);
                    gameModel.setInit(init);
                    jframeMain.updateCards(gameModel);
                    jframeMain.showCard("inGame");
                } else {
                    JOptionPane.showMessageDialog(null, "Il nome utente non e' valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(JPanelMostraPartite.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Seleziona una riga valida", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonCaricaActionPerformed

    private void jButtonCancella1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancella1ActionPerformed

        int rigaSelezionata = jTablePartite.getSelectedRow();
        if (rigaSelezionata != -1) {
            try {
                int idPartita = (int) jTablePartite.getModel().getValueAt(rigaSelezionata, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler "
                        + "cancellare la partia?", "Conferma", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    InitDatabase.eliminaPartita(idPartita);
                    jframeMain.updateCards(gameModel);
                    jframeMain.showCard("showGames");
                }
            } catch (IOException ex) {
                Logger.getLogger(JPanelMostraPartite.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Seleziona una riga valida", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButtonCancella1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancella1;
    private javax.swing.JButton jButtonCarica;
    private javax.swing.JButton jButtonIndietro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePartite;
    // End of variables declaration//GEN-END:variables
}
