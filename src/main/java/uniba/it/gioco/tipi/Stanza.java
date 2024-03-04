/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Nikita
 */
public class Stanza {
    private String nome;
    private String descrizione;
    private Set<Oggetto> oggettiNecessari;
    private Set<Oggetto> oggettiPresentiStanza;
    private HashMap<String, Stanza> collegamenti;

    public Stanza(String nome, String descrizione, Set<Oggetto> oggettiNecessari, Set<Oggetto> oggettiPresentiStanza, HashMap<String, Stanza> collegamenti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.oggettiNecessari = new HashSet<>();
        this.oggettiPresentiStanza = oggettiPresentiStanza;
        this.collegamenti = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
    
    

    public Set<Oggetto> getOggettiNecessari() {
        return oggettiNecessari;
    }

    public Set<Oggetto> getOggettiPresentiStanza() {
        return oggettiPresentiStanza;
    }

    public HashMap<String, Stanza> getCollegamenti() {
        return collegamenti;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stanza: ").append(nome).append("\n");
        sb.append("Descrizione: ").append(descrizione).append("\n");
        
        sb.append("Oggetti presenti:\n");
        for (Oggetto oggetto : oggettiPresentiStanza) {
            sb.append("- ").append(oggetto.getNome()).append("\n");
        }
        
        sb.append("Oggetti necessari:\n");
        for (Oggetto oggetto : oggettiNecessari) {
            sb.append("- ").append(oggetto.getNome()).append("\n");
        }
        sb.append("Collegamenti:\n");
        for (Map.Entry<String, Stanza> entry : collegamenti.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue().getNome()).append("\n");
        }
        
        return sb.toString();
    }
    
    
    
    
  

        
}