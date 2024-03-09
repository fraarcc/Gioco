/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Giocatore;

/**
 *
 * @author Nikita
 */
public class Input extends Thread {
    private JTextField inputTestoCampo;
    private Giocatore giocatoreCorrente;
    private LogicaComandi logicaComandi;
    private Init init;

    public Input(JTextField inputTestoCampo, Giocatore giocatoreCorrente,Init init) {
        this.inputTestoCampo = inputTestoCampo;
        this.giocatoreCorrente = giocatoreCorrente;
        this.init = init;
        this.logicaComandi = new LogicaComandi(giocatoreCorrente,init);
        
    }

    @Override
    public void run() {
        inputTestoCampo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaComando(inputTestoCampo.getText().trim());
            }
        });
    }

    public void inviaComando(String inputTesto) {
         
        System.out.println("Input: " + inputTesto); // Stampa l'input ricevuto
        List<String> tokens = Parser.parse(inputTesto);
        System.out.println("Tokens: " + tokens); // Stampa i token estratti dall'input

        if (!inputTesto.isEmpty() && Parser.isValidCommand(inputTesto)) {
            logicaComandi.gestioneComandi(inputTesto,giocatoreCorrente);
            inputTestoCampo.setText("");
        } else {
            System.out.println("Comando non valido");
        }
    }
}

