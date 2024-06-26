package uniba.it.gioco.tipi;

import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;

public class Inventario implements Serializable {

    private Set<Oggetto> oggetti;

    public Inventario() {
        this.oggetti = new HashSet<>();
    }

    public void aggiungiOggetto(Oggetto oggetto) {
        oggetti.add(oggetto);
    }

    public void rimuoviOggetto(Oggetto oggetto) {
        oggetti.remove(oggetto);
    }

    public boolean contieneOggetto(Oggetto oggetto) {
        return oggetti.contains(oggetto);
    }

    public Set<Oggetto> getOggetti() {
        return oggetti;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Inventario:\n\n");

        if (oggetti.isEmpty()) {
            stringBuilder.append("Nessun oggetto nell'inventario.\n\n");
        } else {
            for (Oggetto oggetto : oggetti) {
                stringBuilder.append("- ");
                stringBuilder.append(oggetto.getNome());
                stringBuilder.append(":\n");

                String descrizione = oggetto.getDescrizione();
                int lunghezzaMassimaPerRiga = 50;
                int index = 0;
                while (index < descrizione.length()) {
                    stringBuilder.append("   ");
                    stringBuilder.append(descrizione.substring(index, Math.min(index + lunghezzaMassimaPerRiga, descrizione.length())));
                    stringBuilder.append("\n\n");
                    index += lunghezzaMassimaPerRiga;
                }
            }
        }

        return stringBuilder.toString();
    }
}
