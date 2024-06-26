package uniba.it.gioco.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uniba.it.gioco.GameModel;

public class JFrameMain extends javax.swing.JFrame {

    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private GameModel gameModel;

    public JFrameMain(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        initComponents();
        creazioneCards();
        gestioneUscita();
    }

    private void creazioneCards() {
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        getContentPane().add(cardsPanel, BorderLayout.CENTER);
        cardsPanel.add(new JPanelMenu(this, gameModel), "mainMenu");
        cardsPanel.add(new JPanelNuovoGioco(this, gameModel), "newGame");
        cardsPanel.add(new JPanelPartita(this, gameModel), "inGame");
        cardsPanel.add(new JPanelMostraPartite(this, gameModel), "showGames");
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("SAN DUZIANO");
    }

    public void gestioneUscita() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(JFrameMain.this, "Vuoi davvero chiudere l'applicazione?"
                        + "\nI tuoi progressi potrebbero essere persi", "Conferma", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrameMain.EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrameMain.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    public void showCard(String cardName) {
        cardLayout.show(cardsPanel, cardName);
    }

    public void updateCards(GameModel gameModel) throws IOException {
        cardsPanel.removeAll();
        cardsPanel.add(new JPanelMenu(this, gameModel), "mainMenu");
        cardsPanel.add(new JPanelNuovoGioco(this, gameModel), "newGame");
        cardsPanel.add(new JPanelPartita(this, gameModel), "inGame");
        cardsPanel.add(new JPanelMostraPartite(this, gameModel), "showGames");
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
