/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.json.JSONObject;
import uniba.it.gioco.GameModel;
import uniba.it.gioco.database.InitDatabase;
import uniba.it.gioco.gui.JFrameLucchetto;
import uniba.it.gioco.parser.Parser;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Direzione;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Inventario;
import uniba.it.gioco.tipi.Npc;
import uniba.it.gioco.tipi.Oggetto;
import uniba.it.gioco.tipi.Stanza;
import uniba.it.gioco.tipi.TipoNpc;

/**
 *
 * @author Nikita
 */
public class LogicaComandi {

    private Input input;
    private Output output;
    private Giocatore giocatore;
    private Init init;
    private JTextArea jOutputTestoArea;
    private JTextField jInputTestoArea;
    private List<Stanza> stanze;
    private LogicaDialoghi logicaDialoghi;
    private GameModel gameModel;

    public LogicaComandi(Giocatore giocatore, Init init, JTextArea jOutputTestoArea, JTextField jInputTestoArea,
            Output output, List<Stanza> stanze, LogicaDialoghi logicaDialoghi, Input input) {
        this.giocatore = giocatore;
        this.init = init;
        this.jOutputTestoArea = jOutputTestoArea;
        this.jInputTestoArea = jInputTestoArea;
        this.output = output;
        this.stanze = stanze;
        this.logicaDialoghi = logicaDialoghi;
        this.input = input;
    }

    public void gestioneComandi(String inputTesto) {
        List<Comando> comandi = init.getCommandsAsList();

        String tipoComando = Parser.getCommandType(inputTesto).toLowerCase();
        boolean comandoTrovato = false; // Flag per indicare se un comando è stato trovato
        for (Comando comando : comandi) {
            if (comando.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(tipoComando))) {
                eseguiComando(comando, inputTesto);
                comandoTrovato = true;
                break;
            }
        }
        if (!comandoTrovato) {
            jOutputTestoArea.append("Comando non valido. Digita 'aiuto' per vedere la lista dei comandi disponibili.\n");
        }

    }

    private void eseguiComando(Comando comando, String inputTesto) {
        switch (comando.getType()) {
            case NORD:
                eseguiComandoNord(giocatore);
                break;
            case SUD:
                eseguiComandoSud(giocatore);
                break;
            case EST:
                eseguiComandoEst(giocatore);
                break;
            case OVEST:
                eseguiComandoOvest(giocatore);
                break;
            case APRI:
                if (giocatore.getStanzaCorrente().getNome().equals("bagno") && !stanze.get(5).isAperto()) {
                    Thread thread = new Thread(() -> {
                        boolean risultato = eseguiComandoApri();
                        SwingUtilities.invokeLater(() -> {
                            if (risultato) {
                                stanze.get(5).setAperto(true);
                                jOutputTestoArea.append("Lo sgabuzzino è stato aperto!\nProva a proseguire.\n");
                            } else {
                                jOutputTestoArea.append("Non sei riuscito ad aprire il lucchetto, riprova.\n");
                            }
                        });
                    });
                    thread.start();
                } else {
                    jOutputTestoArea.append("Non puoi aprire nessuna stanza. \n");
                }

                break;
            case PARLA:
                if (giocatore.getStanzaCorrente().haNpc()) {
                    List<String> esaminaNpcTokens = gestioneComandiComplessi(inputTesto);
                    eseguiComandoParlaNpc(giocatore, esaminaNpcTokens, inputTesto);

                } else {
                    jOutputTestoArea.append("Non puoi parlare con nessuno in questa stanza \n");
                }
                break;
            case INDOSSA:
                if (controlloCamice(giocatore)) {
                    List<String> tokens = gestioneComandiComplessi(inputTesto);
                    eseguiComandoIndossa(giocatore, tokens);
                } else {
                    jOutputTestoArea.append("Nel tuo inventario non ci sono oggetti da indossare\n");
                }
                break;
            case RICHIEDI:
                if (giocatore.getStanzaCorrente().haNpc()) {
                    if (giocatore.getStanzaCorrente().getNome().equals("atrio")) {
                        List<String> oggettoTokens = gestioneComandiComplessi(inputTesto);
                        eseguiComandoRichiediChiavi(giocatore, oggettoTokens);
                    }
                    if (giocatore.getStanzaCorrente().getNome().equals("farmacia")) {
                        List<String> oggettiTokens = gestioneComandiComplessi(inputTesto);
                        eseguiComandoRichiediProdottoChimici(giocatore, oggettiTokens);
                    }
                }
                break;
            case RACCOGLI:
                if (controlloOggettiStanza(giocatore)) {
                    List<String> oggettoDesiderato = gestioneComandiComplessi(inputTesto);
                    if (!oggettoDesiderato.isEmpty()) {
                        eseguiComandoRaccogli(giocatore, oggettoDesiderato);
                    } else {
                        jOutputTestoArea.append("Specificare il nome dell'oggetto\n");
                    }
                }
                break;
            case LEGGI:
                if (controlloInventario(giocatore)) {
                    List<String> esaminaOggettoToken = gestioneComandiComplessi(inputTesto);
                    eseguiComandoLeggi(giocatore, esaminaOggettoToken);
                } else {
                    jOutputTestoArea.append("Non puoi usare questo comando, continua ad esplorare \n");
                }
                break;
            case LANCIATI:
                //finestra
                break;
            case AIUTO:
                eseguiComandoAiuto();
                break;
            case INVENTARIO:
                eseguiComandoInventario(giocatore);
                break;
            case OSSERVA:
                osservaStanza(giocatore);
                break;
            case ESCI:
                System.exit(0);
                break;
            case SALVA:
                eseguiComandoSalva();
                break;
            case SUGGERIMENTO:
                jOutputTestoArea.append("Ecco il suggerimento che ti do:\n");
                eseguiComandoSuggerimenti();
                break;
            default:
                System.out.println("Tipo di comando non gestito: " + comando.getType() + "\n");
                break;
        }
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

    public void osservaStanza(Giocatore giocatore) {
        Stanza stanzaCorrente = giocatore.getStanzaCorrente();
        Set<Oggetto> oggettiStanza = stanzaCorrente.getOggettiPresentiStanza();

        if (!oggettiStanza.isEmpty()) {
            jOutputTestoArea.append("Oggetti presenti nella stanza:\n");
            for (Oggetto oggetto : oggettiStanza) {
                jOutputTestoArea.append("  Nome: " + oggetto.getNome() + "\n");
                stampaDescrizione(oggetto, 40);
            }
        } else {
            jOutputTestoArea.append("In questa stanza non sono presenti oggetti.\n\n");
        }

        Npc npcStanzaCorrente = stanzaCorrente.getNpc();
        if (npcStanzaCorrente != null) {
            jOutputTestoArea.append("Noti anche: " + npcStanzaCorrente.getNome() + "\n\n");
        }
    }

    public void stampaDescrizione(Oggetto oggetto, int lunghezzaMassimaPerRiga) {
        String descrizione = oggetto.getDescrizione();
        StringBuilder descrizioneFormattata = new StringBuilder("  Descrizione:\n  ");

        int count = 0;
        for (char c : descrizione.toCharArray()) {
            if (count >= lunghezzaMassimaPerRiga && c != ' ') {
                descrizioneFormattata.append("\n  ");
                count = 0;
            }
            descrizioneFormattata.append(c);
            count++;
        }
        jOutputTestoArea.append(descrizioneFormattata.toString() + "\n\n");
    }

    public boolean controlloOggettiStanza(Giocatore giocatore) {
        if (!giocatore.getStanzaCorrente().getOggettiPresentiStanza().isEmpty()) {
            return true;
        } else {
            jOutputTestoArea.append("Non ci sono oggetti in questa stanza \n");
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
            jOutputTestoArea.append("Hai raccolto l'oggetto: " + oggettoDesiderato.getNome() + "\n");
        } else {
            jOutputTestoArea.append("L'oggetto '" + tokensOggettoDesiderato + "' non è presente nella stanza\n");
        }
    }

    public void eseguiComandoInventario(Giocatore giocatore) {
        Inventario inventario = giocatore.getInventario();
        jOutputTestoArea.append(inventario.toString());
    }

    public void eseguiComandoParlaNpc(Giocatore giocatore, List<String> tokens, String inputTesto) {
        if (tokens.size() != 1) {
            // La lista dei token deve contenere un solo elemento
            jOutputTestoArea.append("Inserire un nome corretto per parlare. \n");
            return;
        }
        String nomeNpc = tokens.get(0).toLowerCase();
        // Verifica se l'NPC è nella stessa stanza del giocatore
        Npc npcStanza = giocatore.getStanzaCorrente().getNpc();
        if (!npcStanza.getNome().equalsIgnoreCase(nomeNpc)) {
            jOutputTestoArea.append("Non puoi parlare con " + nomeNpc + " in questa stanza. \n");
            return;
        }

        switch (nomeNpc) {
            case "farmacista":
                if (npcStanza.getTipo() == TipoNpc.FARMACISTA) {
                    List<String> dialoghiFarmacista = npcStanza.getDialoghi();
                    jOutputTestoArea.append(dialoghiFarmacista.get(0));
                }
                break;
            case "barista":
                if (npcStanza.getTipo() == TipoNpc.BARISTA) {
                    List<String> dialoghiBarista = npcStanza.getDialoghi();
                    jOutputTestoArea.append(dialoghiBarista.get(0) + "\n");
                }
                break;
            case "guardie":
                if (npcStanza.getTipo() == TipoNpc.GUARDIE) {
                    if (!npcStanza.isVisitato()) {
                        logicaDialoghi.gestioneGuardie(npcStanza, jInputTestoArea, jOutputTestoArea);
                        System.out.println("Output jInputTestoArea:" + jInputTestoArea.getText());

                    } else {
                        jOutputTestoArea.append("Non puoi più parlare con le guardie. \n");
                    }
                }
                break;
            case "uomo":
                // Logica per l'interazione con un uomo misterioso
                // Rimossa per brevità
                break;
            case "dottoressa":
                if (npcStanza.getTipo() == TipoNpc.DOTTORESSA) {
                    // Logica per l'interazione con una dottoressa
                    jOutputTestoArea.append("-Dottoressa: Salve, cosa posso fare per lei? \n");

                }
                break;
            default:
                jOutputTestoArea.append("Non puoi parlare con " + nomeNpc + ". \n");
                break;
        }

    }

    public void eseguiComandoLeggi(Giocatore giocatore, List<String> tokens) {
        if (tokens.size() != 1) {
            jOutputTestoArea.append("Non puoi osservare questo oggetto. \n");
            return;
        }
        String oggettoDaLeggere = tokens.get(0).toLowerCase();
        Set<Oggetto> inventarioGiocatore = giocatore.getInventario().getOggetti();
        boolean oggettoTrovato = false;
        for (Oggetto oggetto : inventarioGiocatore) {
            if (oggetto.getNome().equalsIgnoreCase(oggettoDaLeggere)) {
                switch (oggettoDaLeggere) {
                    case "ticket":
                        jOutputTestoArea.append("Il ticket riporta su un lato la scritta: A3258.\n"
                                + "La scritta sembra appena stampata. Dev'essere un biglietto utilizzato da qualcuno pochi minuti fa.\n");
                        oggettoTrovato = true;
                        break;
                    case "foglietto": // Da testare
                        jOutputTestoArea.append("Leggi queste righe scritte una sotto l'altra: sodio cl, potass, calcio cl, sodio ace.\n");
                        oggettoTrovato = true;
                        break;
                    case "seconda meta' foglietto": // Da testare
                        jOutputTestoArea.append("Leggi le seguenti parole scritte in colonna: oruro, io cloruro, oruro diidrato, tato triidrato\n");
                        oggettoTrovato = true;
                        break;
                    default:
                        jOutputTestoArea.append("Non puoi leggere questo oggetto. \n");
                        return;
                }
                break;
            }
        }
        if (!oggettoTrovato) {
            jOutputTestoArea.append("L'oggetto specificato non è presente nell'inventario. \n");
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
            jOutputTestoArea.append("Devi essere nell'atrio per poter richiedere le chiavi del bagno. \n");
            return;
        }
        System.out.println("Chiavi");
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
            jOutputTestoArea.append("Ecco le chiavi del bagno. \n");
            stanze.get(4).setAperto(true);
        } else {
            // Se le chiavi del bagno non sono presenti nell'inventario dell'NPC o non sono specificate nei token, mostra un messaggio di errore
            jOutputTestoArea.append("Le chiavi del bagno non sono disponibili. \n");
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
            jOutputTestoArea.append("Hai indossato il camice. \n");
        } else {
            // Se la condizione non è soddisfatta, mostra un messaggio di errore
            jOutputTestoArea.append("Non puoi indossare questo oggetto. \n");
        }
    }

    public void eseguiComandoRichiediProdottoChimici(Giocatore giocatore, List<String> oggettiTokens) {
        Npc farmacista = giocatore.getStanzaCorrente().getNpc();
        Set<Oggetto> inventarioFarmacista = farmacista.getOggettiNpc();
        List<String> dialoghiFarmacista = farmacista.getDialoghi();
        boolean camiceIndossato = giocatore.getInventario().getOggetti().stream()
                .anyMatch(oggetto -> oggetto.getId() == 14);

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
                            jOutputTestoArea.append(dialoghiFarmacista.get(1));
                        } else {
                            jOutputTestoArea.append("Farmacista: Non ho il sodio cloruro disponibile al momento.\n");
                        }
                    }
                    if (potassioCloruro) {
                        Oggetto potassioCloruroOggetto = null;
                        for (Oggetto oggetto : inventarioFarmacista) {
                            if (oggetto.getId() == 4) {
                                potassioCloruroOggetto = oggetto;
                                break;
                            }
                        }
                        if (potassioCloruroOggetto != null) {
                            inventarioFarmacista.remove(potassioCloruroOggetto);
                            giocatore.aggiungiOggettoInventario(potassioCloruroOggetto);
                            jOutputTestoArea.append(dialoghiFarmacista.get(1));
                        } else {
                            jOutputTestoArea.append("Farmacista: Non ho il potassio cloruro disponibile al momento.\n");
                        }
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
                            jOutputTestoArea.append(dialoghiFarmacista.get(1));
                        } else {
                            jOutputTestoArea.append("Farmacista: Non ho il calcio cloruro diidrato disponibile al momento.\n");
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
                            jOutputTestoArea.append(dialoghiFarmacista.get(1));
                        } else {
                            jOutputTestoArea.append("Farmacista: Non ho il sodio acetato triidrato disponibile al momento.\n");
                        }
                    }
                } else {
                    jOutputTestoArea.append("Farmacista: Devi specificare i prodotti chimici correttamente.\n");
                }
            } else {
                jOutputTestoArea.append("Farmacista: Non ho questi prodotti.\n");
            }
        } else {
            // Se il camice non viene indossato, mostra i dialoghi del farmacista
            jOutputTestoArea.append(dialoghiFarmacista.get(2) + "\n");
            jOutputTestoArea.append(dialoghiFarmacista.get(3) + "\n");
        }
    }

    public void eseguiComandoSalva() {
        gameModel = input.getGameModel();

        InitDatabase.salvaPartita(giocatore.getNickname(), gameModel);
        jOutputTestoArea.append("Partita Salvata correttamente \n");
    }

    public static CompletableFuture<Boolean> apriLucchetto() {
        CompletableFuture<Boolean> risultato = new CompletableFuture<>();

        // Avvia il frame del lucchetto in un thread separato
        Thread thread = new Thread(() -> {
            JFrameLucchetto frame = new JFrameLucchetto((statoGioco) -> {
                risultato.complete(statoGioco);
            });
            frame.setVisible(true);
        });
        thread.start();

        return risultato;
    }

    public boolean eseguiComandoApri() {
        CompletableFuture<Boolean> risultato = apriLucchetto();
        boolean stato;
        try {
            stato = risultato.get(); // Questo bloccherà il thread finché il risultato non sarà disponibile
            if (stato) {
                System.out.println("Il lucchetto è stato sbloccato!");
            } else {
                System.out.println("Il lucchetto non è stato sbloccato.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            stato = false; // Gestione dell'errore
        }

        return stato;
    }

    public void eseguiComandoAiuto() {
        try {
            String filePath = ".\\res\\aiuto.txt";
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder aiutoFile = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                aiutoFile.append(line).append("\n");
            }
            reader.close();

            jOutputTestoArea.append(aiutoFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Utilizzo API AdviceSlipAPI il quale fornisce consigli casuali
    public void eseguiComandoSuggerimenti() {
        String consiglioInItaliano = null;
        try {
            // URL dell'API Advice Slip
            URL url = new URL("https://api.adviceslip.com/advice");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Legge la risposta
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Analizza la risposta JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject slip = jsonResponse.getJSONObject("slip");
            String consiglio = slip.getString("advice");

            consiglioInItaliano = traduciInItaliano(consiglio);

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder formattedAdvice = new StringBuilder();
        int charsPerLine = 50;
        int charCount = 0;
        for (int i = 0; i < consiglioInItaliano.length(); i++) {
            char c = consiglioInItaliano.charAt(i);
            if (c == ' ' && charCount > charsPerLine) {
                formattedAdvice.append('\n');
                charCount = 0;
            } else {
                formattedAdvice.append(c);
                charCount++;
            }
        }

        jOutputTestoArea.append(formattedAdvice.toString() + "\n");
    }

    //Utilizzo l'API MyMemory Translation Memory per tradurre il cosiglio in italiano
    private static String traduciInItaliano(String testo) {
        String testoTradotto = null;
        try {
            String urlEncodedText = URLEncoder.encode(testo, "UTF-8");
            URL url = new URL("https://api.mymemory.translated.net/get?q=" + urlEncodedText + "&langpair=en|it");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Analizza la risposta JSON per ottenere la traduzione
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject responseData = jsonResponse.getJSONObject("responseData");
            testoTradotto = responseData.getString("translatedText");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return testoTradotto;
    }
}
