/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
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
    private JButton bottoneInvio;
    private List<Stanza> stanze;
    private boolean dialogo = false;

    public LogicaComandi(Giocatore giocatore, Init init, JTextArea outputTestoCampo, JTextField inputTestoCampo, JButton bottoneInvio, Output output, List<Stanza> stanze) {
        this.giocatore = giocatore;
        this.init = init;
        this.parser = new Parser();
        this.outputTestoCampo = outputTestoCampo;
        this.inputTestoCampo = inputTestoCampo;
        this.bottoneInvio = bottoneInvio;
        this.output = output;
        this.stanze = stanze;
    }

    public void gestioneComandi(String inputTesto, Giocatore giocatore, JTextField inputTestoCampo) {
        List<Comando> comandi = init.getCommandsAsList();
        if (dialogo == false) {
            String tipoComando = parser.getCommandType(inputTesto).toLowerCase();
            boolean comandoTrovato = false; // Flag per indicare se un comando è stato trovato
            for (Comando comando : comandi) {
                if (comando.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(tipoComando))) {
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
                            if (giocatore.getStanzaCorrente().haNpc()) {
                                List<String> esaminaNpcTokens = gestioneComandiComplessi(inputTesto);
                                eseguiComandoParlaNpc(giocatore, esaminaNpcTokens, inputTesto);
                            } else {
                                outputTestoCampo.append("Non puoi parlare con nessuno in questa stanza \n");
                            }
                            break;
                        case INDOSSA:
                            //usabile solo se si ha camice nell'inventario (camice indossato non visibile all'utente)
                            System.out.println("Comando INDOSSA trovato");
                            if (controlloCamice(giocatore)) {
                                List<String> tokens = gestioneComandiComplessi(inputTesto);
                                eseguiComandoIndossa(giocatore, tokens);
                            } else {
                                outputTestoCampo.append("Nel tuo inventario non ci sono oggetti da indossare\n");
                            }

                            break;
                        case RICHIEDI:
                            System.out.println("Comando RICHIEDI trovato");
                            if (giocatore.getStanzaCorrente().haNpc()) {
                                 System.out.println("asdasdasdasd");
                                if (giocatore.getStanzaCorrente().getNome().equals("atrio")) {
                                    List<String> oggettoTokens = gestioneComandiComplessi(inputTesto);
                                    eseguiComandoRichiediChiavi(giocatore, oggettoTokens);
                                }
                               if (giocatore.getStanzaCorrente().getNome().equals("farmacia")) {
                                     System.out.println("asdasdasdasd");
                                    List<String> oggettiTokens = gestioneComandiComplessi(inputTesto);
                                    eseguiComandoRichiediProdottoChimici(giocatore, oggettiTokens);
                                }
                            }
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
                            if (controlloInventario(giocatore)) {
                                List<String> esaminaOggettoToken = gestioneComandiComplessi(inputTesto);
                                eseguiComandoLeggi(giocatore, esaminaOggettoToken);
                            } else {
                                outputTestoCampo.append("Non puoi usare questo comando, continua ad esplorare \n");
                            }
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
                outputTestoCampo.append("Comando non valido\n");
            }
        }
    }

    public boolean getDialogo() {
        return dialogo;
    }

    public void eseguiComandoSud(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.SUD, output, stanze);
    }

    public void eseguiComandoNord(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.NORD, output, stanze);
    }

    public void eseguiComandoEst(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.EST, output, stanze);
    }

    public void eseguiComandoOvest(Giocatore giocatore) {
        giocatore.spostaGiocatore(init, Direzione.OVEST, output, stanze);
    }

    public void eseguiComandoAiuto(JTextArea outputTestoCampo) {
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
        if (npcStanzaCorrente != null) {
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

    public void eseguiComandoParlaNpc(Giocatore giocatore, List<String> tokens, String inputTesto) {
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
                    List<String> dialoghiBarista = npcStanza.getDialoghi();
                    outputTestoCampo.append(dialoghiBarista.get(0) + "\n");
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
                        outputTestoCampo.append("La risposta e' sbagliata, continua ad esplorare \n");
                        inputTestoCampo.removeActionListener(this);
                    }
                    // Altrimenti, gestisci ulteriori input dell'utente o altro qui
                }
            });

            // Action listener per il bottone "invioButton"
            bottoneInvio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ottieni l'input dell'utente dal campo di testo
                    String inputUtente = inputTestoCampo.getText().trim();
                    // Esegui la stessa logica di gestione dell'input dell'utente
                    // come nel ActionListener del campo di testo
                    if (init.controlloDialogo(inputUtente, "si")) {
                        outputTestoCampo.append(guardie.getIndovinello().getDomanda() + "\n");
                        // Rimuovi l'ascoltatore precedente per evitare duplicati
                        inputTestoCampo.removeActionListener(this);
                        // Aggiungi un nuovo ascoltatore per gestire la risposta successiva dell'utente
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
                        outputTestoCampo.append("La risposta e' sbagliata, continua ad esplorare \n");
                        // Rimuovi questo ascoltatore dopo aver gestito la risposta
                        bottoneInvio.removeActionListener(this);
                    }
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
        String oggettoDaLeggere = tokens.get(0).toLowerCase();
        // Verifica se l'oggetto da leggere è presente nell'inventario del giocatore
        Set<Oggetto> inventarioGiocatore = giocatore.getInventario().getOggetti();
        boolean oggettoTrovato = false;
        for (Oggetto oggetto : inventarioGiocatore) {
            if (oggetto.getNome().equalsIgnoreCase(oggettoDaLeggere)) {
                // Se l'oggetto è nell'inventario, verifica quale oggetto è e mostra il testo appropriato
                switch (oggettoDaLeggere) {
                    case "ticket":
                        outputTestoCampo.append("Il ticket riporta su un lato la scritta: A3258.\n"
                                + "La scritta sembra appena stampata. Dev'essere un biglietto utilizzato da qualcuno pochi minuti fa.\n");
                        oggettoTrovato = true;
                        break;
                    case "foglietto":  //Da testare
                        outputTestoCampo.append("Leggi queste righe scritte una sotto l'altra: sodio cl, potass, calcio cl, sodio ace.\n");
                        oggettoTrovato = true;
                        break;
                    case "seconda meta' foglietto": // Da testare
                        outputTestoCampo.append("Leggi le seguenti parole scritte in colonna: oruro, io cloruro, oruro diidrato, tato triidrato\n");
                        oggettoTrovato = true;
                        break;
                    default:
                        // Se l'oggetto da leggere non corrisponde a nessuna delle opzioni previste, mostra un messaggio di errore
                        outputTestoCampo.append("Non puoi leggere questo oggetto. \n");
                        return;
                }
                break; // Esci dal ciclo una volta trovato l'oggetto
            }
        }

        // Se l'oggetto da leggere non è stato trovato nell'inventario, mostra un messaggio di errore
        if (!oggettoTrovato) {
            outputTestoCampo.append("L'oggetto specificato non è presente nell'inventario. \n");
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

    public boolean controlloCamice(Giocatore giocatore) {
        Set<Oggetto> inventarioGiocatore = giocatore.getInventario().getOggetti();
        for (Oggetto oggetto : inventarioGiocatore) {
            if (oggetto.getId() == 9) {
                return true;
            }
        }
        return false;
    }

    public void eseguiComandoRichiediChiavi(Giocatore giocatore, List<String> tokensOggetto) {
        // Verifica se il giocatore si trova nell'atrio
        if (!giocatore.getStanzaCorrente().getNome().equalsIgnoreCase("atrio")) {
            outputTestoCampo.append("Devi essere nell'atrio per poter richiedere le chiavi del bagno. \n");
            return;
        }

        // Ottieni l'NPC barista dalla stanza
        Npc barista = giocatore.getStanzaCorrente().getNpc();
        // Ottieni l'inventario dell'NPC barista
        Set<Oggetto> inventarioNpc = barista.getOggettiNpc();

        // Verifica se le chiavi del bagno sono presenti nell'inventario dell'NPC
        boolean chiaviPresenti = false;
        Oggetto chiaviBagno = null; // Salva un riferimento alle chiavi del bagno se trovate
        for (Oggetto oggetto : inventarioNpc) {
            if (oggetto.getId() == 2) {
                chiaviPresenti = true;
                chiaviBagno = oggetto; // Salva un riferimento alle chiavi del bagno
                break;
            }
        }

        // Verifica se tra i token è presente "chiavi bagno"
        boolean tokenChiaviPresenti = false;
        for (String token : tokensOggetto) {
            if (token.equalsIgnoreCase("chiavi") || token.equalsIgnoreCase("bagno")) {
                tokenChiaviPresenti = true;
                break;
            }
        }

        if (chiaviPresenti && tokenChiaviPresenti) {
            // Rimuovi le chiavi dall'inventario dell'NPC
            inventarioNpc.remove(chiaviBagno);
            // Aggiungi le chiavi all'inventario del giocatore
            giocatore.aggiungiOggettoInventario(chiaviBagno);

            // Esegui la logica per consegnare le chiavi del bagno
            outputTestoCampo.append("Ecco le chiavi del bagno. \n");
            stanze.get(4).setAperto(true);
        } else {
            // Se le chiavi del bagno non sono presenti nell'inventario dell'NPC o non sono specificate nei token, mostra un messaggio di errore
            outputTestoCampo.append("Le chiavi del bagno non sono disponibili. \n");
        }
    }

    public void eseguiComandoIndossa(Giocatore giocatore, List<String> tokens) {
        // Verifica se la lista non è nulla, ha un solo elemento e se quel elemento è "camice"
        if (tokens != null && tokens.size() == 1 && tokens.get(0).equalsIgnoreCase("camice")) {
            Set<Oggetto> inventario = giocatore.getInventario().getOggetti();
            //Elimino il camice dall inventario
            Oggetto camice = giocatore.getInventario().getOggetti()
                    .stream()
                    .filter(oggetto -> oggetto.getId() == 9)
                    .findFirst()
                    .orElse(null);
            giocatore.getInventario().getOggetti().remove(camice);

            Oggetto camiceIndossato = new Oggetto(14, "Camice Indossato", "Un camice bianco da laboratorio, indossato.");
            giocatore.aggiungiOggettoInventario(camiceIndossato);
            outputTestoCampo.append("Hai indossato il camice. \n");
        } else {
            // Se la condizione non è soddisfatta, mostra un messaggio di errore
            outputTestoCampo.append("Non puoi indossare questo oggetto. \n");
        }
    }

    public void eseguiComandoRichiediProdottoChimici(Giocatore giocatore, List<String> oggettiTokens) {
        Npc farmacista = giocatore.getStanzaCorrente().getNpc();
        Set<Oggetto> inventarioFarmacista = farmacista.getOggettiNpc();
        List<String> dialoghiFarmacista = farmacista.getDialoghi();
        boolean camiceIndossato = giocatore.getInventario().getOggetti().stream()
                .anyMatch(oggetto -> oggetto.getId() == 14);
         System.out.print(camiceIndossato);
        if (camiceIndossato) {
            // Se il camice è indossato
            if (oggettiTokens.size() <= 3) {
                // Verifica se i token corrispondono ai prodotti chimici richiesti
                boolean sodioCloruro = oggettiTokens.contains("sodio") && oggettiTokens.contains("cloruro");
                boolean potassioCloruro = oggettiTokens.contains("potassio") && oggettiTokens.contains("cloruro");
                boolean calcioCloruroDiidrato = oggettiTokens.contains("calcio") && oggettiTokens.contains("cloruro") && oggettiTokens.contains("diidrato");
                boolean sodioAcetatoTriidrato = oggettiTokens.contains("sodio") && oggettiTokens.contains("acetato") && oggettiTokens.contains("triidrato");

                if (sodioCloruro || potassioCloruro || calcioCloruroDiidrato || sodioAcetatoTriidrato) {
                    if (sodioCloruro) {
                        Oggetto sodioCloruroOggetto = null;
                        // Cerca l'oggetto "sodioCloruro" nell'inventario del farmacista
                        for (Oggetto oggetto : inventarioFarmacista) {
                            if (oggetto.getId() == 3) {
                                sodioCloruroOggetto = oggetto;
                                break;
                            }
                        }
                        if (sodioCloruroOggetto != null) {
                            // Rimuovi l'oggetto "sodioCloruro" dall'inventario del farmacista
                            inventarioFarmacista.remove(sodioCloruroOggetto);
                            // Aggiungi l'oggetto "sodioCloruro" all'inventario del giocatore
                            giocatore.aggiungiOggettoInventario(sodioCloruroOggetto);
                            outputTestoCampo.append(dialoghiFarmacista.get(1));
                        } else {
                            outputTestoCampo.append("Farmacista: Non ho il sodio cloruro disponibile al momento.\n");
                        }
                    }
                    if (potassioCloruro) {
                        Oggetto potassioCloruroOggetto = null;
                        for(Oggetto oggetto : inventarioFarmacista){
                            if(oggetto.getId() == 4){
                                potassioCloruroOggetto = oggetto;
                                break;
                            }
                        }
                        if(potassioCloruroOggetto != null){
                            inventarioFarmacista.remove(potassioCloruroOggetto);
                            giocatore.aggiungiOggettoInventario(potassioCloruroOggetto);
                            outputTestoCampo.append(dialoghiFarmacista.get(1));
                        } else{
                            outputTestoCampo.append("Farmacista: Non ho il potassio cloruro disponibile al momento.\n");
                        }

                        if (calcioCloruroDiidrato) {
                            Oggetto calcioCloruroDiidratoOggetto = null;
                            for (Oggetto oggetto : inventarioFarmacista) {
                                if (oggetto.getId() == 5) {
                                    calcioCloruroDiidratoOggetto = oggetto;
                                    break;
                                }
                            }
                            if (calcioCloruroDiidratoOggetto != null) {
                                inventarioFarmacista.remove(calcioCloruroDiidratoOggetto);
                                giocatore.aggiungiOggettoInventario(calcioCloruroDiidratoOggetto);
                                outputTestoCampo.append(dialoghiFarmacista.get(1));
                            } else {
                                outputTestoCampo.append("Farmacista: Non ho il calcio cloruro diidrato disponibile al momento.\n");
                            }
                        }
                        if (sodioAcetatoTriidrato) {
                              Oggetto sodioAcetatoTriidratoOggetto = null;
                            for (Oggetto oggetto : inventarioFarmacista) {
                                if (oggetto.getId() == 6) {
                                    sodioAcetatoTriidratoOggetto = oggetto;
                                    break;
                                }
                            }
                            if (sodioAcetatoTriidratoOggetto != null) {
                                inventarioFarmacista.remove(sodioAcetatoTriidratoOggetto);
                                giocatore.aggiungiOggettoInventario(sodioAcetatoTriidratoOggetto);
                                outputTestoCampo.append(dialoghiFarmacista.get(1));
                            } else {
                                outputTestoCampo.append("Farmacista: Non ho il sodio acetato triidrato disponibile al momento.\n");
                            }
                        }   
                    }
                    // Aggiungi qui la logica per gli altri prodotti chimici richiesti
                } else {
                    outputTestoCampo.append("Farmacista: Devi specificare i prodotti chimici correttamente.\n");
                }
            } else {
                outputTestoCampo.append("Farmacista: Non ho questi prodotti.\n");
            }
        } else {
            // Se il camice non viene indossato, mostra i dialoghi del farmacista
            outputTestoCampo.append(dialoghiFarmacista.get(2) + "\n");
            outputTestoCampo.append(dialoghiFarmacista.get(3) + "\n");
        }
    }

}
