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
import uniba.it.gioco.tipi.Npc;
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
    private List<Stanza> stanze;
    

    public LogicaComandi(Giocatore giocatore, Init init, JTextArea outputTestoCampo, Output output, List<Stanza> stanze) {
        this.giocatore = giocatore;
        this.init = init;
        this.parser = new Parser();
        this.outputTestoCampo = outputTestoCampo;
        this.output = output;
        this.stanze = stanze;
    }

    public void gestioneComandi(String inputTesto,Giocatore giocatore) {
        List<Comando> comandi = init.getCommandsAsList();
       // comandi.toString();
      //  System.out.println("Input: " + inputTesto);
        String tipoComando = parser.getCommandType(inputTesto).toLowerCase();
      //  System.out.println("Tipo comando riconosciuto: " + tipoComando);

        boolean comandoTrovato = false; // Flag per indicare se un comando è stato trovato

        for (Comando comando : comandi) {
           // System.out.println("Comando in esaminzazione: " + comando.getType());
             //System.out.println("Type: " + comandi);
            if (comando.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(tipoComando))) {
            //    System.out.println("Alias trovato: " + comando.getAliases());
            //   System.out.println("Type: " + comando.getType());
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
                        //Solo in sgabuzzino
                        break;
                    case PARLA:
                        System.out.println("Comando PARLA trovato");
                        break;
                    case INDOSSA:
                        System.out.println("Comando INDOSSA trovato");
                        //Solo in sgabuzzino con camice
                        break;
                    case RICHIEDI:
                        System.out.println("Comando RICHIEDI trovato");
                        break;
                    case RACCOGLI:
                        System.out.println("Comando RACCOGLI trovato");
                        if (controlloOggettiStanza(giocatore)) {
                            List<String> oggettoDesiderato = gestioneComandiComplessi(inputTesto);
                            if (!oggettoDesiderato.isEmpty()) {
                                eseguiComandoRaccogli(giocatore, oggettoDesiderato);
                            } else {
                                outputTestoCampo.append("Specificare il nome dell'oggetto");
                            }
                        }
                        break;
                    case LEGGI:
                        System.out.println("Comando LEGGI trovato");
                        //Solo per foglietto (sgabuzzino) dentro camice e ticket
                        break;
                    case LANCIATI:
                        System.out.println("Comando LANCIATI trovato");
                        //finsetra 
                        break;
                    case AIUTO:
                        System.out.println("Comando AIUTO trovato");
                        eseguiComandoAiuto(outputTestoCampo);
                        break;
                    case INVENTARIO:
                         System.out.println("Comando INV trovato");
                         eseguiComandoInventario(giocatore);
                         break;
                    case OSSERVA:
                        System.out.println("OSSERVA RILEVATO");
                        osservaStanza(giocatore);
                        break;
                    case ESCI:
                        System.out.println("Comando ESCI trovato");
                        System.exit(0);
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
        giocatore.spostaGiocatore(init, Direzione.SUD, output,stanze);
    }

    public void eseguiComandoNord(Giocatore giocatore) {
      giocatore.spostaGiocatore(init, Direzione.NORD, output,stanze);
    }

    public void eseguiComandoEst(Giocatore giocatore) {
     giocatore.spostaGiocatore(init, Direzione.EST, output,stanze);
    }

    public void eseguiComandoOvest(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.OVEST, output,stanze);
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
                outputTestoCampo.append("- " + oggetto.getNome() + "\n" + oggetto.getDescrizione() + "\n");
                
            }
        } else {
            outputTestoCampo.append("In questa stanza non sono presenti oggetti\n");
        }
        Npc npcStanzaCorrente = stanzaCorrente.getNpc();
        if(npcStanzaCorrente != null){
            outputTestoCampo.append("Tuttavia nella stanza noti: " + npcStanzaCorrente.getNome() + "\n");
        }
         
    }

    public boolean controlloOggettiStanza(Giocatore giocatore) {
        if (!giocatore.getStanzaCorrente().getOggettiPresentiStanza().isEmpty()) {
            return true;
        } else {
            outputTestoCampo.append("Non ci sono oggetti in questa stanza");
            return false;
        }
    }
    
    public List<String> gestioneComandiComplessi(String input) {
        List<String> tokensComando = Parser.parse(input);
        tokensComando.remove(0);
        return tokensComando;
    }

    public void eseguiComandoRaccogli(Giocatore giocatore, List<String> tokensOggettoDesiderato) {

        System.out.println(tokensOggettoDesiderato);
        Stanza stanzaCorrente = giocatore.getStanzaCorrente();
        Set<Oggetto> oggettiStanza = stanzaCorrente.getOggettiPresentiStanza();
        Oggetto oggettoDesiderato = null;
        for (Oggetto oggetto : oggettiStanza) {
            List<String> tokensNomeOggetto = Parser.parse(oggetto.getNome());
            if (tokensNomeOggetto.equals(tokensOggettoDesiderato)) {
                oggettoDesiderato = oggetto;
                break;
            }
        }

        if (oggettoDesiderato != null) {
            giocatore.aggiungiOggettoInventario(oggettoDesiderato);
            stanzaCorrente.rimuoviOggettoDallaStanza(oggettoDesiderato);
            outputTestoCampo.append("Hai raccolto l'oggetto: " + oggettoDesiderato.getNome() + "\n");
        } else {
            outputTestoCampo.append("L'oggetto '" + tokensOggettoDesiderato + "' non è presente nella stanza\n");
        }
    }

    public void eseguiComandoInventario(Giocatore giocatore) {
        Set<Oggetto> inventario = giocatore.getInventario().getOggetti();
        if (!inventario.isEmpty()) {
            outputTestoCampo.append("Hai i seguenti oggetti nel tuo inventario \n");
            for (Oggetto oggetto : inventario) {
                outputTestoCampo.append(oggetto.getNome() + "\n\n");
            }
        } else {
            outputTestoCampo.append("Non ci sono oggetti nel tuo inventario");
        }

    }

}
