/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author Nikita
 */
public class Inventario implements Serializable {
    
     private Set<Oggetto> oggetti;
    
   public Inventario(){
       oggetti = new HashSet<>();
   }
    public Inventario(Set<Oggetto> oggetti) {
        this.oggetti = oggetti;
    }
    
    public void rimuovi(Oggetto oggetto){
        if(this.oggetti.contains(oggetto )){
            this.oggetti.remove(oggetto );
            
        }
    }
    public boolean stato(){
        return (getOggetti().size() > 0);
    }
    
    public void aggiungi(Oggetto oggetto){
        this.oggetti.add(oggetto);
    }
    
    public Set<Oggetto> getOggetti(){
        return oggetti;
    }
    
    
     @Override
    public String toString(){
        String messaggio = new String();
        if(!oggetti.isEmpty()){
            messaggio = "Oggetti presenti nel tuo inventario";
            for(Oggetto x : oggetti )
                messaggio = messaggio + ("\n -" + x.getNome());
            }else{
                    messaggio = "Non vi è alcun oggetto nel tuo inventario";
                 }
         return messaggio;
        }
        
    }
    