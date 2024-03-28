package uniba.it.gioco.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import java.util.*;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Inventario;
import uniba.it.gioco.tipi.Stanza;

public class Init {

    private final ObjectMapper objectMapper;
    private final Set<String> stopWords;
    private final Map<String, Comando> commands;

    public Init() {
        this.objectMapper = new ObjectMapper();
        this.stopWords = loadStopWords("./res/stopwords.txt");
        this.commands = loadCommandsFromJson("./res/comandi.json");
    }

    private Set<String> loadStopWords(String filePath) {
        Set<String> stopWords = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                stopWords.add(scanner.nextLine().trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento delle stop words: " + e.getMessage());
        }
        return Collections.unmodifiableSet(stopWords);
    }

    private Map<String, Comando> loadCommandsFromJson(String jsonFilePath) {
        Map<String, Comando> commands = new HashMap<>();
        try {
            List<Comando> commandList = objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Comando>>() {
            });
            for (Comando command : commandList) {
                commands.put(command.getName(), command);
                for (String alias : command.getAliases()) {
                    commands.put(alias, command);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dei comandi da JSON: " + e.getMessage());
        }
        return Collections.unmodifiableMap(commands);
    }

    public Set<String> getStopWords() {
        return stopWords;
    }

    public Map<String, Comando> getCommands() {
        return commands;
    }

    public List<Stanza> inizializzaStanze() throws IOException {
        String filePath = "./res/collegamentoStanze.json";
        return loadJSON(filePath, new TypeReference<List<Stanza>>() {
        });
    }

    public Giocatore inizializzaGiocatore(int idUtente, String nickname, Stanza stanzaIniziale) {
        // Aggiungere controllo nickname da database per adesso senza controlli
        return new Giocatore(idUtente, nickname, stanzaIniziale, new Inventario());
    }

    private <T> List<T> loadJSON(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(new File(filePath), typeReference);
    }

    public Stanza loadStanzaFromJson(String stanzaNome) throws IOException {
        String filePath = "./res/collegamentoStanze.json";
        List<Stanza> stanze = loadJSON(filePath, new TypeReference<List<Stanza>>() {
        });
        for (Stanza stanza : stanze) {
            if (stanza.getNome().equals(stanzaNome)) {
                return stanza;
            }
        }
        throw new IOException("Stanza non trovata nel file JSON: " + stanzaNome);
    }

    public List<Comando> getCommandsAsList() {
        return new ArrayList<>(commands.values());
    }

}
