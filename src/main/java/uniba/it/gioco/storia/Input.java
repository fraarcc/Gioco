/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import uniba.it.gioco.parser.Parser;

/**
 *
 * @author Nikita
 */
public class Input extends Thread {

    private JTextField inputTestoCampo;

    public Input(JTextField inputTestoCampo) {
        this.inputTestoCampo = inputTestoCampo;
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
        System.out.println(inputTesto);
        List<String> tokens = Parser.parse(inputTesto);
        System.out.println(tokens);
        if (!inputTesto.isEmpty()) {
            if (Parser.isValidCommand(inputTesto)) {
                String esaminaComando = Parser.getCommandType(inputTesto);
                System.out.println(esaminaComando);
                switch (esaminaComando) {
                    case "SIMPLE":
                        System.out.println("Simple");
                        break;

                    case "PARAMETERIZED":
                        System.out.println("Parameterized");
                        break;

                    default:
                        System.out.println("Non valido");
                        break;
                }

                inputTestoCampo.setText("");
            } else {
                System.out.println("Comando non valido");
            }
        }
    }
}
