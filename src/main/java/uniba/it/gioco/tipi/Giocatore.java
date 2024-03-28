package uniba.it.gioco.tipi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import uniba.it.gioco.utils.Init;
import uniba.it.gioco.utils.Output;

public class Giocatore implements Serializable {

    private int idUtente;
    private String nickname;
    private Stanza stanzaCorrente;
    private Inventario inventario;

    public Giocatore(int idUtente, String nickname, Stanza stanzaCorrente, Inventario zaino) {
        this.idUtente = idUtente;
        this.nickname = nickname;
        this.stanzaCorrente = stanzaCorrente;
        this.inventario = new Inventario();
    }

    public Giocatore() {

    }

    public int getIdUtente() {
        return idUtente;
    }

    public String getNickname() {
        return nickname;
    }

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public void aggiungiOggettoInventario(Oggetto oggettoDaInserire) {
        inventario.aggiungiOggetto(oggettoDaInserire);
    }

    public void spostaGiocatore(Init init, Direzione direzione, Output output, List<Stanza> stanze) {
        Stanza stanzaAttuale = getStanzaCorrente();
        Map<Direzione, Stanza> connessioneStanze = stanzaAttuale.getConnessioneStanze();

        // Verifica se esiste una connessione nella direzione specificata
        if (connessioneStanze.containsKey(direzione)) {
            Stanza nuovaStanza = connessioneStanze.get(direzione);

            // Cerca la nuova stanza nella lista delle stanze
            for (Stanza s : stanze) {
                if (s.getNome().equals(nuovaStanza.getNome())) {
                    nuovaStanza = s;
                    break;
                }
            }

            if (nuovaStanza.isAperto()) {
                // Imposta la nuova stanza corrente
                setStanzaCorrente(nuovaStanza);
                output.cambioStanza();
            } else {
                output.stanzaChiusaMsg();
            }
        } else {
            output.direzioneErrataMsg();
        }
    }

    public void cambioStanzaDiretto(List<Stanza> stanze, int posStanza, Output output) {
        setStanzaCorrente(stanze.get(posStanza));
        output.cambioStanza();
    }

    @Override
    public String toString() {
        return "Giocatore{"
                + "id=" + idUtente
                + ", nome='" + nickname + '\''
                + ", stanzaAttuale=" + stanzaCorrente
                + ", inventario=" + inventario
                + '}';
    }
}
