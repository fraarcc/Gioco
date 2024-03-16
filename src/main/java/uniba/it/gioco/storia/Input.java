/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class Input extends Thread {
    private JTextField inputTestoCampo;
    private Giocatore giocatoreCorrente;
    private LogicaComandi logicaComandi;
    private Init init;
    private JTextArea outputTestoCampo;
    private JButton bottoneInvio;
    private Output output;
    private List<Stanza> stanze;
    
    public Input(JTextField inputTestoCampo, Giocatore giocatoreCorrente,Init init, JTextArea outputTestoCampo, JButton bottoneInvio,Output output,List<Stanza> stanze) {
        this.inputTestoCampo = inputTestoCampo;
        this.giocatoreCorrente = giocatoreCorrente;
        this.init = init;
        this.outputTestoCampo = outputTestoCampo; 
        this.bottoneInvio = bottoneInvio;
        this.output = output;
        this.stanze = stanze;
        this.logicaComandi = new LogicaComandi(giocatoreCorrente,init, outputTestoCampo,inputTestoCampo,bottoneInvio,output,stanze);
     
        
    }

    @Override
    public void run() {
        inputTestoCampo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaComando(inputTestoCampo.getText().trim(),inputTestoCampo);
            }
        });
    }

    public void inviaComando(String inputTesto, JTextField inputTestoCampo) {

        System.out.println("Input: " + inputTesto); // Stampa l'input ricevuto
        List<String> tokens = Parser.parse(inputTesto);
        System.out.println("Tokens: " + tokens); // Stampa i token estratti dall'input

        if (!inputTesto.isEmpty() && Parser.isValidCommand(inputTesto)) {
            logicaComandi.gestioneComandi(inputTesto, giocatoreCorrente, inputTestoCampo);
            inputTestoCampo.setText("");
        } else if (logicaComandi.getDialogo() == true) {
            logicaComandi.gestioneComandi(inputTesto, giocatoreCorrente, inputTestoCampo);
        } 
    }
}
