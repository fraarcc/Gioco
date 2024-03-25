package uniba.it.gioco.tipi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Npc implements Serializable {

    private String nome;
    private TipoNpc tipo;
    private boolean visitato = false;
    private Set<Oggetto> oggettiNpc;
    private List<String> dialoghi;
    private Indovinello indovinello;

    public Npc() {

    }

    public Npc(String nome, TipoNpc tipo, Set<Oggetto> oggettiNpc) {
        this.nome = nome;
        this.tipo = tipo;
        this.oggettiNpc = oggettiNpc;
        this.dialoghi = new ArrayList<>();
        if (tipo == TipoNpc.GUARDIE) {
            this.indovinello = Indovinello.GUARDIA;
        } else {
            this.indovinello = null;
        }
    }

    public boolean isVisitato() {
        return visitato;
    }

    public void setVisitato(boolean visitato) {
        this.visitato = visitato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TipoNpc tipo) {
        this.tipo = tipo;
    }

    public void setOggettiNpc(Set<Oggetto> oggettiNpc) {
        this.oggettiNpc = oggettiNpc;
    }

    public void setDialoghi(List<String> dialoghi) {
        this.dialoghi = dialoghi;
    }

    public void setIndovinello(Indovinello indovinello) {
        this.indovinello = indovinello;
    }

    public String getNome() {
        return nome;
    }

    public TipoNpc getTipo() {
        return tipo;
    }

    public List<String> getDialoghi() {
        return dialoghi;
    }

    public Indovinello getIndovinello() {
        return indovinello;
    }

    public Set<Oggetto> getOggettiNpc() {
        return oggettiNpc;
    }

    @Override
    public String toString() {
        return "Npc{" + "nome=" + nome + ", tipo=" + tipo + ", oggettiNpc=" + oggettiNpc + ", dialoghi=" + dialoghi + ", indovinello=" + indovinello + '}';
    }

}
