/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uniba.it.gioco.storia.Init;

/**
 *
 * @author Nikita
 */
public class Giocatore {

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

    public void spostaGiocatore(Init init, Direzione direzione) {
        Stanza stanzaAttuale = getStanzaCorrente();
        Map<Direzione, Stanza> connessioneStanze = stanzaAttuale.getConnessioneStanze();

        // Verifica se esiste una connessione nella direzione specificata
        if (connessioneStanze.containsKey(direzione)) {
            try {
                Stanza nuovaStanza = connessioneStanze.get(direzione);
                nuovaStanza = init.loadStanzaFromJson(nuovaStanza.getNome());
                setStanzaCorrente(nuovaStanza);
                System.out.println("Ti sei spostato nella stanza: " + nuovaStanza.getNome());
                System.out.println("Descrizione: " + nuovaStanza.getDescrizione());

                // Aggiorna l'inventario del giocatore, ecc. se necessario
            } catch (IOException ex) {
                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Non puoi andare in quella direzione.");
        }
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
