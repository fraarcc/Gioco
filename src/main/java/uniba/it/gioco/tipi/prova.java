package uniba.it.gioco.tipi;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uniba.it.gioco.database.InitDatabase;
import uniba.it.gioco.storia.Init;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nikita
 */
public class prova {
    public static void main(String[] args) {
        
        
      Oggetto o1 = new Oggetto(1,"prova1", "descrizione 1");
      Oggetto o2 = new Oggetto(2,"prova2", "descrizione2");
      Oggetto o3 = new Oggetto(3,"prova3","descrizione 3");
       
       Set<Oggetto> insiemeogg = new HashSet<>();
       insiemeogg.add(o1);
       insiemeogg.add(o2);
       insiemeogg.add(o3);
  
     
       
       
       
       //Stanza s1= new Stanza(1,"stanza1","Descrizione1","Ispeziona1",true, insiemeogg);
       
      //System.out.println(s1.toString());
      
      InitDatabase.creaConnessione();
     Init init = new Init();
      Inventario invUser = new Inventario();
      
      try{
          List<Stanza> stanze = init.inizializzaStanze();
          System.out.println(stanze.toString());
          Giocatore user = new Giocatore(1,"user",stanze.get(0),invUser);
          System.out.println(user.toString());
          user.setStanzaCorrente(stanze.get(1));
          System.out.println(user.toString());
      } catch (IOException ex){
          ex.printStackTrace();
      }
      
    
      
      
     
    }
            
}
        
    
  
