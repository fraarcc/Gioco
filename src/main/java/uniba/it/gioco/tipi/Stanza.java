/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.util.Set;

/**
 *
 * @author Nikita
 */
public class Stanza {
    private final int id;
    private String nome;
    private String descrizione;
    private String ispezionaStanza;
    
    private boolean visibile= true;
    
    private Stanza nord = null;
    
    private Stanza sud = null;
    
    private Stanza est = null;
    
    private Stanza ovest = null;
    
    private Set<Oggetto> oggettiPerAccedere;
         
    private Set<Oggetto> oggettiStanza;
    
    //costruttori
    
    public Stanza(int id){
        
        this.id = id; 
    }
    
    public Stanza(int id, String nome, String descrizione){
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        
    }

    public Stanza(int id, String nome, String descrizione, String ispezionaStanza, boolean visibile, Set<Oggetto> oggettiStanza) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.ispezionaStanza = ispezionaStanza;
        this.visibile = visibile;
        this.oggettiStanza = oggettiStanza;
    }
    
    //getter e setter
    
    
    public int getId(){
        return id;
        
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getDescrizione(){
        return descrizione;
    }
    
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
        
    }
    public Set<Oggetto> getOggettiPerAccedere(){
        return oggettiPerAccedere;
    }
    
    public boolean isVisibile(){
        return visibile;
    }
    
    public String getIspezionaStanza(){
        return ispezionaStanza;
    }
    public void setIspezionaStanza(String ispezionaStanza){
        this.ispezionaStanza = ispezionaStanza;
    }
    
    public void setVisibile(boolean visibile){
        this.visibile = visibile ;
    }
    public Stanza getSud(){
        return sud;
    }
    public void setSud(Stanza sud){
        this.sud = sud;
    }
    public Stanza getNord(){
        return nord;
    }
    public void setNord(Stanza nord){
        this.nord = nord;
    }
    public Stanza getEst(){
        return est;
    }
    public void setEst(Stanza est){
        this.est = est;
    }
    public Stanza getOvest(){
        return ovest;
    }
    public void setOvest(Stanza ovest){
        this.ovest = ovest;
    }
    public Set<Oggetto> oggettiStanza (){
        return oggettiStanza;
    }        

        
}