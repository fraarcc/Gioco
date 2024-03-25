package uniba.it.gioco.tipi;

import java.io.Serializable;

public class Indovinello implements Serializable {

    private String domanda;
    private String risposta;

    public static final Indovinello GUARDIA = new Indovinello("", "");

    public Indovinello() {

    }

    public Indovinello(String domanda, String risposta) {
        this.domanda = domanda;
        this.risposta = risposta;
    }

    public String getDomanda() {
        return domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public boolean controllaRisposta(String rispostaUtente) {
        return rispostaUtente.equalsIgnoreCase(risposta);
    }
}
