package uniba.it.gioco.tipi;

import java.util.HashSet;
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
        
        
      Oggetto o1 = new Oggetto(1,"prova1", "descrizione 1");
      Oggetto o2 = new Oggetto(2,"prova2", "descrizione2");
      Oggetto o3 = new Oggetto(3,"prova3","descrizione 3");
       
       Set<Oggetto> insiemeogg = new HashSet<>();
       insiemeogg.add(o1);
       insiemeogg.add(o2);
       insiemeogg.add(o3);
       Inventario inv = new Inventario(insiemeogg);
       System.out.println(inv.toString());
       inv.rimuovi(o3);
       System.out.println(inv.toString());
       
       
       
       //Stanza s1= new Stanza(1,"stanza1","Descrizione1","Ispeziona1",true, insiemeogg);
       
      //System.out.println(s1.toString());
      
      
       
    }
}