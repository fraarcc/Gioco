/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package uniba.it.gioco;

import uniba.it.gioco.gui.JFrameMain;


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
