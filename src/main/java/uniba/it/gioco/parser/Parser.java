/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uniba.it.gioco.storia.Init;
import uniba.it.gioco.tipi.Comando;

/**
 *
 * @author Nikita
 */
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
        List<String> tokens = parse(input);
        if (tokens.isEmpty()) {
            return false;
        }

        String commandName = tokens.get(0);
        Comando command = commands.get(commandName);
        if (command == null) {
            return false;
        }

        return command.isValid(tokens);
    }

    public static String getCommandType(String input) {
        List<String> tokens = parse(input);
        if (tokens.isEmpty()) {
            return "Invalid";
        }

        String commandName = tokens.get(0);
        Comando command = commands.get(commandName);
        return command != null ? command.getType().toString() : "Invalid";
    }
}
