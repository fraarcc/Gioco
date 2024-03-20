/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.io.Serializable;

/**
 *
 * @author Nikita
 */
public class Indovinello implements Serializable {
    private String domanda;
    private String risposta;
    
    public static final Indovinello GUARDIA = new Indovinello("","");
    
    public Indovinello(){
        
    }
    
    public Indovinello(String domanda,String risposta){
        this.domanda = domanda;
        this.risposta = risposta;
    }
    
    public String getDomanda(){
        return domanda;
    }
    
    public String getRisposta(){
        return risposta;
    }
    
    public boolean controllaRisposta(String rispostaUtente) {
        return rispostaUtente.equalsIgnoreCase(risposta);
    }
}
