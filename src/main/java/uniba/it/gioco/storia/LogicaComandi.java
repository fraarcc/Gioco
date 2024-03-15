/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Direzione;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Npc;
import uniba.it.gioco.tipi.Oggetto;
import uniba.it.gioco.tipi.Stanza;
import uniba.it.gioco.tipi.TipoNpc;

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
    private JTextField inputTestoCampo;
    private List<Stanza> stanze;
    private boolean dialogo = false;

    public LogicaComandi(Giocatore giocatore, Init init, JTextArea outputTestoCampo,JTextField inputTestoCampo ,Output output, List<Stanza> stanze) {
        this.giocatore = giocatore;
        this.init = init;
        this.parser = new Parser();
        this.outputTestoCampo = outputTestoCampo;
        this.inputTestoCampo = inputTestoCampo;
        this.output = output;
        this.stanze = stanze;
    }

    public void gestioneComandi(String inputTesto,Giocatore giocatore,JTextField inputTestoCampo) {
        List<Comando> comandi = init.getCommandsAsList();
       // comandi.toString();
      //  System.out.println("Input: " + inputTesto);
        if(dialogo == false){
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
                        //Solo in bagno se sta armadietto prendi scotch
                        break;
                    case PARLA:
                        System.out.println("Comando PARLA trovato");
                        if(giocatore.getStanzaCorrente().haNpc()){
                            List<String> esaminaNpcTokens = gestioneComandiComplessi(inputTesto);
                            eseguiComandoParlaNpc(giocatore,esaminaNpcTokens,inputTesto);
                        } else outputTestoCampo.append("Non puoi parlare con nessuno in questa stanza \n");
                        break;
                    case INDOSSA:
                        System.out.println("Comando INDOSSA trovato");
                        //usabile solo se si ha camice nell'inventario (camice indossato non visibile all'utente)
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
                        if(controlloInventario(giocatore)){
                            List<String> esaminaOggettoToken = gestioneComandiComplessi(inputTesto);
                        eseguiComandoLeggi(giocatore,esaminaOggettoToken);
                        } else outputTestoCampo.append("Non puoi usare questo comando, continua ad esplorare \n");
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
    }
    
    public boolean getDialogo(){
        return dialogo;
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
            outputTestoCampo.append("Tuttavia nella stanza noti: " + npcStanzaCorrente.getNome()+ "\n");
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
    
    public void eseguiComandoParlaNpc(Giocatore giocatore, List<String> tokens,String inputTesto) {
        if (tokens.size() != 1) {
            // La lista dei token deve contenere un solo elemento
            outputTestoCampo.append("Inserire un nome corretto per parlare. \n");
            return;
        }
        
        

        String nomeNpc = tokens.get(0).toLowerCase();

        // Verifica se l'NPC è nella stessa stanza del giocatore
        Npc npcStanza = giocatore.getStanzaCorrente().getNpc();
        if (!npcStanza.getNome().equalsIgnoreCase(nomeNpc)) {
            outputTestoCampo.append("Non puoi parlare con " + nomeNpc + " in questa stanza. \n");
            return;
        }

        switch (nomeNpc) {
            case "farmacista":
                if (npcStanza.getTipo() == TipoNpc.FARMACISTA) {
                    // Logica per l'interazione con un farmacista
                    System.out.println("Stai parlando con un farmacista.");
                }
                break;
            case "barista":
                if (npcStanza.getTipo() == TipoNpc.BARISTA) {
                    // Logica per l'interazione con un barista
                    System.out.println("Stai parlando con un barista.");
                }
                break;
            case "guardie":
                if (npcStanza.getTipo() == TipoNpc.GUARDIE) {
                    if (npcStanza.isVisitato() == false) {
                        gestioneGuardie(giocatore, npcStanza, inputTesto);
                        System.out.println("Stai parlando con le guardie.");
                    } else {
                        outputTestoCampo.append("Non puoi piu' parlare con le guardie. \n");
                    }
                }
                break;
            case "uomo":
                if (npcStanza.getTipo() == TipoNpc.UOMO_MISTERIOSO) {
                    // Logica per l'interazione con un uomo misterioso
                    System.out.println("Stai parlando con un uomo misterioso.");
                }
                break;
            case "dottoressa":
                if (npcStanza.getTipo() == TipoNpc.DOTTORESSA) {
                    // Logica per l'interazione con una dottoressa
                    System.out.println("Stai parlando con una dottoressa.");
                }
                break;
            default:
                // Token non corrisponde a nessun tipo di NPC conosciuto
                outputTestoCampo.append("Non puoi parlare con " + nomeNpc + ". \n");
                break;
        }
    }

    // Metodo per gestire il dialogo con le guardie
    public void gestioneGuardie(Giocatore giocatore, Npc guardie, String inputTesto) {
        dialogo = true;
        if (guardie.getTipo() == TipoNpc.GUARDIE) {
            List<String> dialoghiGuardie = guardie.getDialoghi();
            outputTestoCampo.append(dialoghiGuardie.get(0) + "\n");

            // Gestione dell'input dell'utente
            inputTestoCampo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputUtente = inputTestoCampo.getText().trim();
                    inputTestoCampo.setText(""); 
                    // Esegui la logica del dialogo con le guardie in base all'input dell'utente
                    if (init.controlloDialogo(inputUtente, "si")) {
                        outputTestoCampo.append(guardie.getIndovinello().getDomanda() + "\n");
                        // Rimuovi l'ascoltatore precedente per evitare di aggiungere duplicati
                        inputTestoCampo.removeActionListener(this);

                        // Aggiungi un nuovo ascoltatore per ascoltare la risposta successiva dell'utente
                        inputTestoCampo.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String inputRisposta = inputTestoCampo.getText().trim();
                                inputTestoCampo.setText(""); 

                                // Esegui la logica della risposta dell'utente
                                if (guardie.getIndovinello().controllaRisposta(inputRisposta)) {
                                    outputTestoCampo.append(dialoghiGuardie.get(2) + "\n");
                                    stanze.get(1).setAperto(true);
                                    stanze.get(0).getNpc().setVisitato(true);
                                    dialogo = false;
                                    // Continua con la gestione del dialogo o altre azioni
                                } else {
                                    outputTestoCampo.append(dialoghiGuardie.get(1) + "\n");
                                    dialogo = false;
                                    // Puoi gestire ulteriori tentativi o terminare il dialogo
                                }

                                // Rimuovi questo ascoltatore dopo aver gestito la risposta
                                inputTestoCampo.removeActionListener(this);
                            }
                        });
                     
                    } else {
                        dialogo = false;
                        outputTestoCampo.append("La risposta e' sbagliata, continua ad esprolarare \n");
                        inputTestoCampo.removeActionListener(this);
                        
                    }
                    // Altrimenti, gestisci ulteriori input dell'utente o altro qui
                }
            });
        }
    }
    
    public void eseguiComandoLeggi(Giocatore giocatore, List<String> tokens) {
        if (tokens.size() != 1) {
            // La lista dei token deve contenere un solo elemento
            outputTestoCampo.append("Non puoi osservare questo oggetto. \n");
            return;
        }

        Set<Oggetto> inventarioGiocatore = giocatore.getInventario().getOggetti();
        for (Oggetto oggetto : inventarioGiocatore) {
            if (oggetto.getNome().equalsIgnoreCase(tokens.get(0))) {
                {
                    outputTestoCampo.append("Il ticket riporta su un lato la scritta: A3258.\n"
                            + "La scritta sembra appena stampata. Dev'essere un biglietto utilizzato da qualcuno pochi minuti fa.\n");
                    return;
                }
            }
            outputTestoCampo.append("L'oggetto specificato non e' esistente\n");
        }
    }

    
    public boolean controlloInventario(Giocatore giocatore) {
        Set<Oggetto> inventarioGiocatore = giocatore.getInventario().getOggetti();
        for (Oggetto oggetto : inventarioGiocatore) {
            if (oggetto.getId() == 1 || oggetto.getId() == 10 || oggetto.getId() == 12) {
                return true;
            }
        }
        return false;

    }
}
