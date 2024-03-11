/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JTextArea;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Direzione;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class LogicaComandi {

    private Parser parser;
    private Giocatore giocatore;
    private Init init;
    private JTextArea outputTestoCampo;

    public LogicaComandi(Giocatore giocatore, Init init, JTextArea outputTestoCampo) {
        this.giocatore = giocatore;
        this.init = init;
        this.parser = new Parser();
        this.outputTestoCampo = outputTestoCampo;
    }

    public void gestioneComandi(String inputTesto,Giocatore giocatore) {
        List<Comando> comandi = init.getCommandsAsList();
        comandi.toString();
        System.out.println("Input: " + inputTesto);
        String tipoComando = parser.getCommandType(inputTesto).toLowerCase();
        System.out.println("Tipo comando riconosciuto: " + tipoComando);

        boolean comandoTrovato = false; // Flag per indicare se un comando è stato trovato

        for (Comando comando : comandi) {

            if (comando.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(tipoComando))) {
                System.out.println("Alias trovato: " + tipoComando);

                // Confronta il tipo di comando con quelli definiti nella classe Comando
                switch (comando.getType()) {
                    case NORD:
                        System.out.println("Comando NORD trovato");
                        eseguiComandoNord(giocatore);
                        break;
                    case SUD:
                        System.out.println("Comando SUD trovato");
                        eseguiComandoSud(giocatore);
                        break;
                    case EST:
                        System.out.println("Comando EST trovato");
                        eseguiComandoEst(giocatore);
                        break;
                    case OVEST:
                        System.out.println("Comando OVEST trovato");
                        break;
                    case APRI:
                        System.out.println("Comando APRI trovato");
                        break;
                    case PARLA:
                        System.out.println("Comando PARLA trovato");
                        break;
                    case INDOSSA:
                        System.out.println("Comando INDOSSA trovato");
                        break;
                    case RICHIEDI:
                        System.out.println("Comando RICHIEDI trovato");
                        break;
                    case RACCOGLI:
                        System.out.println("Comando RACCOGLI trovato");
                        break;
                    case LEGGI:
                        System.out.println("Comando LEGGI trovato");
                        break;
                    case LANCIATI:
                        System.out.println("Comando LANCIATI trovato");
                        break;
                    case AIUTO:
                        System.out.println("Comando AIUTO trovato");
                        eseguiComandoAiuto(outputTestoCampo);
                        break;
                    case ESCI:
                        System.out.println("Comando ESCI trovato");
                        break;
                    default:
                        System.out.println("Tipo di comando non gestito: " + comando.getType());
                        break;
                }

                comandoTrovato = true; // Imposta il flag a true se un comando è stato trovato
                break; // Esci dal ciclo una volta trovato un comando
            }
        }

        if (!comandoTrovato) {
            System.out.println("Comando non valido"); // Stampato solo se nessun comando è stato trovato
        }
    }
    
    public void eseguiComandoSud(Giocatore giocatore){
       
        System.out.println(giocatore.toString());
        giocatore.spostaGiocatore(init, Direzione.SUD);
         System.out.println(giocatore.toString());
         Stanza stanzaNuova = giocatore.getStanzaCorrente();
        System.out.println(stanzaNuova.toString());
    }
    public void eseguiComandoNord(Giocatore giocatore){
        giocatore.spostaGiocatore(init, Direzione.NORD);

    }
    
    
    
    public void eseguiComandoAiuto(JTextArea outputTestoCampo){
        
        outputTestoCampo.setText("Comando aiuto rilevato");
    }
    
    public void eseguiComandoEst(Giocatore giocatore){
        giocatore.spostaGiocatore(init, Direzione.EST);
    }
    public void eseguiComandoOvest(Giocatore giocatore){
        giocatore.spostaGiocatore(init, Direzione.OVEST);
    }
    
     
}  


