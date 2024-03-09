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
    public boolean isValid(List<String> tokens) {
    String commandName = tokens.get(0);
    return match(commandName); // Verifica se il nome del comando corrisponde al comando
}
    
    public boolean match(String input) {
        if (name.equalsIgnoreCase(input)) {
            return true;
        }
        for (String alias : aliases) {
            if (alias.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
    return "Comando{name='" + name + "', type=" + type + ", aliases=" + aliases + "}";
}
}

