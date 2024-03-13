/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Nikita
 */
public class Npc {
    private String nome;
    private TipoNpc tipo;
    private Set<Oggetto> oggettiNpc;
    private List<String> dialoghi;
    private Indovinello indovinello;
    
    public Npc(){
        
    }
    
    public Npc(String nome, TipoNpc tipo,Set<Oggetto> oggettiNpc){
        this.nome = nome;
        this.tipo = tipo;
        this.oggettiNpc = oggettiNpc;
        this.dialoghi = new ArrayList<>();
        if (tipo == TipoNpc.GUARDIE){
            this.indovinello = Indovinello.GUARDIA;            
        } else {
            this.indovinello = null;
        }
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
    
    public String getNome(){
        return nome;
    }
    
    public TipoNpc getTipo(){
        return tipo;
    }
    
    public List<String> getDialoghi(){
        return dialoghi;
    }
    
    public Indovinello getIndovinello(){
        return indovinello;
    }

    public Set<Oggetto> getOggettiNpc() {
        return oggettiNpc;
    }
    
    
}
