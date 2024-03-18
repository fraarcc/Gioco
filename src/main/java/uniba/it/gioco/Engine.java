/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package uniba.it.gioco;

import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import uniba.it.gioco.gui.JFrameMain;


/**
 *
 * @author 39379
 */
public class Engine {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                GameModel gameModel = new GameModel();
                JFrameMain jFrameMain = new JFrameMain(gameModel);
                jFrameMain.setVisible(true);
            } catch (IOException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
