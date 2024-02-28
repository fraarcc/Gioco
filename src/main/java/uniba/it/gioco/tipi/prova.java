package uniba.it.gioco.tipi;

import java.util.LinkedHashSet;
import java.util.Set;



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
        
        
       Oggetto o1 = new Oggetto(1,"prova1");
       Oggetto o2 = new Oggetto(2,"prova2");
       Oggetto o3 = new Oggetto(3,"prova3");
       
       Set<Oggetto> insiemeogg = new LinkedHashSet<>();
       insiemeogg.add(o1);
       insiemeogg.add(o2);
       
       
       Stanza s1= new Stanza(1,"stanza1","Descrizione1","Ispeziona1",true, insiemeogg);
       
      System.out.println(s1.toString());
      
      
       
    }
}
