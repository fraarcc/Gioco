/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco;

import java.io.IOException;
import java.util.List;
import uniba.it.gioco.storia.Init;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Stanza;

/**
 *
 * @author Nikita
 */
public class GameModel {

    private Init init;
    private Giocatore giocatore;

    public GameModel() {
        this.init = new Init();
        this.giocatore = new Giocatore();
    }

    public GameModel inizializzaGioco(String nickname) {
        try {
            List<Stanza> stanze = init.inizializzaStanze();
            Stanza stanzaIniziale = stanze.get(0);
            this.giocatore = init.inizializzaGiocatore(0, nickname, stanzaIniziale);
            if (this.giocatore == null) {
                throw new IllegalStateException("Impossibile inizializzare il giocatore");
            }
            System.out.println("Giocatore inizializzato con successo: " + this.giocatore.toString());
            return this; // Restituisci l'istanza corrente di GameModel
        } catch (IOException | IndexOutOfBoundsException | IllegalStateException e) {
            e.printStackTrace(); // Stampa lo stack trace dell'eccezione
            return null; // Oppure gestisci l'errore restituendo null
        }
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public String getNomeGiocatore() {
        return giocatore != null ? giocatore.getNickname() : null;
    }
}
