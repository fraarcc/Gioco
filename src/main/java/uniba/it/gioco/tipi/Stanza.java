package uniba.it.gioco.tipi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Stanza implements Serializable {

    private String nome;
    private String descrizione;
    private boolean aperto = true;
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

    public boolean isAperto() {
        return aperto;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
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

    public void setOggettiPresentiStanza(Set<Oggetto> oggettiPresentiStanza) {
        this.oggettiPresentiStanza = oggettiPresentiStanza;
    }

    public void aggiungiConnessione(Direzione direzione, Stanza stanza) {
        connessioneStanze.put(direzione, stanza);
    }

    public void rimuoviOggettoDallaStanza(Oggetto oggettoDaRimuovere) {
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

    public boolean haNpc() {
        return npc != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stanza{nome=").append(nome);
        if (descrizione != null) {
            sb.append(", descrizione=").append(descrizione);
        }
        sb.append(", aperto=").append(aperto);

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
        if (npc != null) {
            sb.append(", npc=").append(npc);
        }
        sb.append("}");
        return sb.toString();
    }
}
