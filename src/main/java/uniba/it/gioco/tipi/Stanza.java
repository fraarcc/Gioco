/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.util.Set;

/**
 *
 * @author Nikita
 */
public class Stanza {
    private final int id;
    private String nome;
    private String descrizione;
    private String ispezionaStanza;
    
    private boolean stato;
    
    private Set<Oggetto> oggettiStanza;

    public Stanza(int id, String nome, String descrizione, String ispezionaStanza, boolean stato, Set<Oggetto> oggettiStanza) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.ispezionaStanza = ispezionaStanza;
        this.stato = stato;
        this.oggettiStanza = oggettiStanza;
    }

    @Override
    public String toString() {
        return "Stanza{" + "id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", ispezionaStanza=" + ispezionaStanza + ", stato=" + stato + ", oggettiStanza=" + oggettiStanza + '}';
    }
        
}
