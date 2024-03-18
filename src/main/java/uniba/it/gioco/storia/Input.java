package uniba.it.gioco.storia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;
import uniba.it.gioco.tipi.TipoComando;

public class Input extends Thread {

    private JTextField inputTestoCampo;
    private Giocatore giocatoreCorrente;
    private LogicaComandi logicaComandi;
    private Init init;
    private JTextArea outputTestoCampo;
    private JButton bottoneInvio;
    private Output output;
    private List<Stanza> stanze;
    private boolean invioInCorso = false;

    public Input(JTextField inputTestoCampo, Giocatore giocatoreCorrente, Init init, JTextArea outputTestoCampo, JButton bottoneInvio, Output output, List<Stanza> stanze) {
        this.inputTestoCampo = inputTestoCampo;
        this.giocatoreCorrente = giocatoreCorrente;
        this.init = init;
        this.outputTestoCampo = outputTestoCampo;
        this.bottoneInvio = bottoneInvio;
        this.output = output;
        this.stanze = stanze;
        this.logicaComandi = new LogicaComandi(giocatoreCorrente, init, outputTestoCampo, inputTestoCampo, bottoneInvio, output, stanze);

    }

    @Override
    public void run() {
        // Aggiungi un ActionListener sia al bottone di invio che al campo di testo per gestire l'invio
        bottoneInvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaComando(inputTestoCampo.getText().trim(), inputTestoCampo);
            }
        });
    }

    public void inviaComando(String inputTesto, JTextField inputTestoCampo) {
        System.out.println("Input: " + inputTesto); // Stampa l'input ricevuto
        List<String> tokens = Parser.parse(inputTesto);
        System.out.println("Tokens: " + tokens); // Stampa i token estratti dall'input   
        if (Parser.isValidCommand(inputTesto)) {
            //Se l'input è valido gestisco i comandi normalmente
            logicaComandi.gestioneComandi(inputTesto);
        }
        if (!Parser.isValidCommand(inputTesto) && (logicaComandi.getDialogo() || Parser.getCommandType(inputTesto) == "PARLA")) {
            //Controllo se il dialogo è attivo e considero anche il comando PARLA per poter entrare e attivare il dialogo per la prima volta
            logicaComandi.gestioneComandi(inputTesto);
        }

        if (((!logicaComandi.getDialogo() && !Parser.isValidCommand(inputTesto))) && !Parser.controlloTokenVuoti(tokens)) {
            //Gestisco l'ultimo caso in cui il dialogo è false, il comando non è valido e i token sono vuoti.
            //Si utilizza un metedo che scorre la lista e controlla se ci sono degli elementi vuoit nella lista.
            //Questo è necessario perchè durante il dialogo venivano salvati degli elementi vuoti nella lista dei token,
            //di conseguenza isEmpty risultava false ma la lista in realtà are vuota
            outputTestoCampo.append("Comando errato \n");
        }
        //In questo modo si riesce a controllare se il comando non è valido e impedire questa stampa durante il dialogo
    }
}
