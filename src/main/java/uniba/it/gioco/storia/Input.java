package uniba.it.gioco.storia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.gui.JFrameMain;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

public class Input {
    private Input input;
    private JTextField jInputTestoArea;
    private JButton jBottoneInvio;
    private Giocatore giocatoreCorrente;
    private Init init;
    private JTextArea jOutputTestoArea;
    private Output output;
    private List<Stanza> stanze;
    private LogicaComandi logicaComandi;
    private LogicaDialoghi logicaDialoghi;
    private GameModel gameModel;


    public Input(JTextField jInputTestoArea,JButton jBottoneInvio, Giocatore giocatoreCorrente, Init init, JTextArea jOutputTestoArea, 
            Output output, List<Stanza> stanze, GameModel gameModel) {
        this.jInputTestoArea = jInputTestoArea;
        this.jBottoneInvio = jBottoneInvio;
        this.giocatoreCorrente = giocatoreCorrente;
        this.init = init;
        this.jOutputTestoArea = jOutputTestoArea;
        this.output = output;
        this.stanze = stanze;
        this.gameModel = gameModel;
        this.input = this;
        // Inizializza prima logicaDialoghi
        this.logicaDialoghi = new LogicaDialoghi(jInputTestoArea, jOutputTestoArea, jBottoneInvio,giocatoreCorrente, stanze);
        // Ora inizializza logicaComandi passando logicaDialoghi
        this.logicaComandi = new LogicaComandi(giocatoreCorrente, init, jOutputTestoArea, jInputTestoArea, output, stanze, logicaDialoghi,input);

        // Ora puoi passare logicaComandi a logicaDialoghi
        this.logicaDialoghi.setLogicaComandi(logicaComandi);
    }


    public void inviaComando(String inputTesto, JTextField inputTestoCampo) {
        

       
            System.out.println("Input: " + inputTesto); // Stampa l'input ricevuto
            List<String> tokens = Parser.parse(inputTesto);
            System.out.println("Tokens: " + tokens); // Stampa i token estratti dall'input  
         
            logicaComandi.gestioneComandi(inputTesto);
        

    }
    public GameModel getGameModel(){
        return gameModel;
    }

}
