/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
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
    private List<Stanza> connessioneStanze;
    
    public Stanza(){
        connessioneStanze = new ArrayList<>();
    }
    
    public Stanza(String nome){
        this.nome = nome;
        connessioneStanze = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
    
    public void aggiungiConnessione(List<Stanza> stanze){
        connessioneStanze.add((Stanza) stanze);
    }
    
    public List<Stanza> getConnessioneStanze(){
        return connessioneStanze;
    }
    
    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Stanza{name=").append(nome);
    if (descrizione != null) {
        sb.append(", descrizione=").append(descrizione);
    }
    if (oggettiNecessari != null && !oggettiNecessari.isEmpty()) {
        sb.append(", oggettiNecessari=").append(oggettiNecessari);
    }
    if (oggettiPresentiStanza != null && !oggettiPresentiStanza.isEmpty()) {
        sb.append(", oggettiPresentiStanza=").append(oggettiPresentiStanza);
    }
    if (connessioneStanze != null && !connessioneStanze.isEmpty()) {
        sb.append(", connessioneStanze=");
        for (Stanza stanza : connessioneStanze) {
            sb.append(stanza.getNome()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // Rimuovi l'ultima virgola e lo spazio
    }
    sb.append("}");
    return sb.toString();
}
}