/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 39379
 */
public class ContenitoreOggetto extends Oggetto implements Serializable{
    
   private List<Oggetto> oggettiContenuti = new ArrayList<>();
 
   public ContenitoreOggetto(int id){
       super(id);
   }

    public List<Oggetto> getOggettiContenuti() {
        return oggettiContenuti;
    }

    public void setOggettiContenuti(List<Oggetto> oggettiContenuti) {
        this.oggettiContenuti = oggettiContenuti;
    }
   
   
   public void aggiungiOggettoContenuto(Oggetto oggetto){
       oggettiContenuti.add(oggetto);
    
   }
   
   public void rimuoviOggettoContenuto(Oggetto oggetto){
       oggettiContenuti.remove(oggetto );
            
   }
   
}
