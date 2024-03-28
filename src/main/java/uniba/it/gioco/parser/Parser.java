package uniba.it.gioco.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uniba.it.gioco.utils.Init;
import uniba.it.gioco.tipi.Comando;

public class Parser {
    private static final Set<String> stopWords;
    private static final Map<String, Comando> commands;

    static {
        Init init = new Init();
        stopWords = init.getStopWords();
        commands = init.getCommands();
    }

    public static List<String> parse(String input) {
        List<String> tokens = new ArrayList<>();
        String[] words = input.trim().toLowerCase().split("\\s+");

        for (String word : words) {
            if (!stopWords.contains(word)) {
                tokens.add(word);
            }
        }

        return tokens;
    }

    public static boolean isValidCommand(String input) {
        return commands.values().stream().anyMatch(command -> command.isValid(input));
    }

    public static String getCommandType(String input) {
        List<String> tokens = parse(input);

        // Controllo della lunghezza dei token
        if (tokens.size() < 1 || tokens.size() > 4) {
            return "Invalid";
        }

        String token = tokens.get(0).toLowerCase(); // Converti il token in minuscolo

        // Cerca il comando basato sugli alias
        for (Comando command : commands.values()) {
            if (command.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(token))) {
                return command.getType().toString();
            }
        }
        return "Invalid";
    }

    public static boolean controlloTokenVuoti(List<String> tokens) {
        for (String token : tokens) {
            if (token == null || token.trim().isEmpty()) {
                return true; // Se trova un elemento null o vuoto, restituisce true
            }
        }
        return false; // Se non trova elementi null o vuoti, restituisce false
    }

}
