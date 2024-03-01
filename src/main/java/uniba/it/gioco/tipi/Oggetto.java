/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;
import java.io.Serializable;


import java.util.Set;
/**
 *
 * @author fraarcc
 */
public class Oggetto implements Serializable{
    private final int id;
    private String nome;
    private String descrizione;
    private Set<String> alias;
    
     public Locazione locazione = Locazione.NOLOCAZIONE;
            
            public enum Locazione {
                
                INVENTARIO, NOLOCAZIONE, OGGCONTAINER, STANZA
            }   
         
    public Oggetto(int id){
        this.id = id;
    }
    public Oggetto(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;    
    }
    
    
    
    public void setNome(String nome){
        this.nome = nome;
        
    }
            
    public String getNome(){
        return nome;
    }
    
    public Locazione getLocazione(){
      return locazione;
    }
    public void setLocazione(Locazione locazione){
        this.locazione = locazione;
    }

    @Override
    public String toString() {
        return "Oggetto{" + "id=" + id + ", descrizione=" + descrizione + '}';
    }
    
    
    
    
}