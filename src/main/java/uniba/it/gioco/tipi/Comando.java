/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.util.List;

/**
 *
 * @author 39379
 */
public class Comando {

    private String name;
    private List<String> aliases;
    private TipoComando type;

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        if (aliases == null) {
            throw new IllegalStateException("La lista degli alias non è stata inizializzata correttamente");
        }
        return aliases;
    }

    public TipoComando getType() {
        System.out.println("Il tipo di comando è: " + type);
        return type;
    }

    public boolean isValid(String input) {
        return match(input);
    }


    public boolean match(String input) {
        String commandName = input.toLowerCase();
        return name.equalsIgnoreCase(commandName) || aliases.stream().anyMatch(alias -> alias.equalsIgnoreCase(commandName));
    }

    @Override
    public String toString() {
        return "Comando{name='" + name + "', type=" + type + ", aliases=" + aliases + "}";
    }
}
