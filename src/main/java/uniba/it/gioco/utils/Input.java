package uniba.it.gioco.utils;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.gui.JPanelPartita;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

public class Input {

    private Input input;
    private LogicaComandi logicaComandi;
    private LogicaDialoghi logicaDialoghi;
    private GameModel gameModel;

    public Input(JPanelPartita jPanelParita, JTextField jInputTestoArea, JButton jBottoneInvio, Giocatore giocatoreCorrente, Init init, JTextArea jOutputTestoArea,
            Output output, List<Stanza> stanze, GameModel gameModel) {
        this.gameModel = gameModel;
        this.input = this;
        this.logicaDialoghi = new LogicaDialoghi(jInputTestoArea, jOutputTestoArea, jBottoneInvio, giocatoreCorrente, stanze);
        this.logicaComandi = new LogicaComandi(jPanelParita, giocatoreCorrente, init, jOutputTestoArea, jInputTestoArea, output, stanze, logicaDialoghi, input);
        this.logicaDialoghi.setLogicaComandi(logicaComandi);
    }

    public void inviaComando(String inputTesto, JTextField inputTestoCampo) {
        logicaComandi.gestioneComandi(inputTesto);
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
