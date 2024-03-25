package uniba.it.gioco.utils;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.gui.JPanelPartita;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

public class Input {
    private Input input;
    private LogicaComandi logicaComandi;
    private LogicaDialoghi logicaDialoghi;
    private GameModel gameModel;

    public Input(JPanelPartita jPanelParita,JTextField jInputTestoArea, JButton jBottoneInvio, Giocatore giocatoreCorrente, Init init, JTextArea jOutputTestoArea,           
            Output output, List<Stanza> stanze, GameModel gameModel) {
        this.gameModel = gameModel;
        this.input = this;
        // Inizializza prima logicaDialoghi
        this.logicaDialoghi = new LogicaDialoghi(jInputTestoArea, jOutputTestoArea, jBottoneInvio, giocatoreCorrente, stanze);
        // Ora inizializza logicaComandi passando logicaDialoghi
        this.logicaComandi = new LogicaComandi(jPanelParita,giocatoreCorrente, init, jOutputTestoArea, jInputTestoArea, output, stanze, logicaDialoghi, input);

        // Ora puoi passare logicaComandi a logicaDialoghi
        this.logicaDialoghi.setLogicaComandi(logicaComandi);
    }

    public void inviaComando(String inputTesto, JTextField inputTestoCampo) {
        List<String> tokens = Parser.parse(inputTesto);       
        logicaComandi.gestioneComandi(inputTesto);
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
