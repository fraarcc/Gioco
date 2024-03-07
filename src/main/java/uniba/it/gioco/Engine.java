/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package uniba.it.gioco;

import uniba.it.gioco.gui.JFrameMain;
import uniba.it.gioco.storia.Init;
import uniba.it.gioco.tipi.Giocatore;

/**
 *
 * @author 39379
 */
public class Engine {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            GameModel gameModel = new GameModel();
            JFrameMain jFrameMain = new JFrameMain(gameModel);
            jFrameMain.setVisible(true);
        });
    }
}
