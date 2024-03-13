/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTextArea;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Direzione;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Oggetto;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class LogicaComandi {
    private Output output;
    private Parser parser;
    private Giocatore giocatore;
    private Init init;
    private JTextArea outputTestoCampo;

    public LogicaComandi(Giocatore giocatore, Init init, JTextArea outputTestoCampo, Output output) {
        this.giocatore = giocatore;
        this.init = init;
        this.parser = new Parser();
        this.outputTestoCampo = outputTestoCampo;
        this.output = output;
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
                        eseguiComandoOvest(giocatore);
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
                    case OSSERVA:
                        System.out.println("OSSERVA RILEVATO");
                        osservaStanza(giocatore);
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

    public void eseguiComandoSud(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.SUD, output);

    }

    public void eseguiComandoNord(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.NORD, output);

    }

    public void eseguiComandoEst(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.EST, output);
    }

    public void eseguiComandoOvest(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.OVEST, output);
    }

    public void eseguiComandoAiuto(JTextArea outputTestoCampo){
        
        outputTestoCampo.append("Comando aiuto rilevato");
    }
    
    public void osservaStanza(Giocatore giocatore) {
        Stanza stanzaCorrente = giocatore.getStanzaCorrente();
        Set<Oggetto> oggettiStanza = stanzaCorrente.getOggettiPresentiStanza();

        if (!oggettiStanza.isEmpty()) {
            outputTestoCampo.append("Oggetti presenti nella stanza:\n");
            for (Oggetto oggetto : oggettiStanza) {
                outputTestoCampo.append("- " + oggetto.toString() + "\n");
            }
        } else {
            outputTestoCampo.append("In questa stanza non sono presenti oggetti\n");
        }
    }
}

