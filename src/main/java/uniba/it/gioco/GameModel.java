/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uniba.it.gioco.gui.JFrameMain;
import uniba.it.gioco.storia.Init;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class GameModel {

    private JFrameMain mainPanel;
    private Init init;
    private Giocatore initGiocatore;

    public GameModel(JFrameMain mainPanel) {
        this.init = new Init();
        this.mainPanel = mainPanel;

    }

    public void confermaNickname(String nickname) {
        try {
            inizializzaGioco(nickname);
            mainPanel.showCard("inGame");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inizializzaGioco(String nickname) throws IOException {
        try {
            List<Stanza> stanze = init.inizializzaStanze();
            Stanza stanzaIniziale = stanze.get(0);
            Giocatore giocatore = init.inizializzaGiocatore(0, nickname, stanzaIniziale);
            System.out.println(giocatore.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
