/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
/**
 *
 * @author fraarcc
 */
public class Oggetto implements Serializable{
    private int id;
    private String nome;
    private String descrizione;
    //private Oggetto ogg
    public Oggetto(){
        
    }
    
    public Oggetto(int id){
        this.id = id;
    }
    
    public Oggetto(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;    
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    
     @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Oggetto\n");
    sb.append("  ID: ").append(id).append("\n");
    sb.append("  Nome: ").append(nome).append("\n");
    sb.append("  Descrizione: ").append(descrizione).append("\n");
    return sb.toString();
}
}
