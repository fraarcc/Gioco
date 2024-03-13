/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;



import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Nikita
 */
public class Stanza {
    private String nome;
    private String descrizione;
    private Set<Oggetto> oggettiNecessari;//Bagno inferiore chiave del bagno, Lab da vedere, Ripostiglio chiave (da leggere) + oggetto con codice 
    private Set<Oggetto> oggettiPresentiStanza;
    private Map<Direzione, Stanza> connessioneStanze;
    private Npc npc;

    public Stanza() {
        connessioneStanze = new HashMap<>();
    }

    public Stanza(String nome) {
        this.nome = nome;
        connessioneStanze = new HashMap<>();
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

    public Map<Direzione, Stanza> getConnessioneStanze() {
        return connessioneStanze;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setOggettiNecessari(Set<Oggetto> oggettiNecessari) {
        this.oggettiNecessari = oggettiNecessari;
    }

    public void setOggettiPresentiStanza(Set<Oggetto> oggettiPresentiStanza) {
        this.oggettiPresentiStanza = oggettiPresentiStanza;
    }

    public void aggiungiConnessione(Direzione direzione, Stanza stanza) {
        connessioneStanze.put(direzione, stanza);
    }
    
    public void rimuoviOggettoDallaStanza(Oggetto oggettoDaRimuovere){
        oggettiPresentiStanza.remove(oggettoDaRimuovere);
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    public Npc getNpc() {
        return npc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setConnessioneStanze(Map<Direzione, Stanza> connessioneStanze) {
        this.connessioneStanze = connessioneStanze;
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
        if (!connessioneStanze.isEmpty()) {
            sb.append(", connessioneStanze=");
            for (Map.Entry<Direzione, Stanza> entry : connessioneStanze.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue().getNome()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Rimuovi l'ultima virgola e lo spazio
        }
        sb.append("}");
        return sb.toString();
    }
}