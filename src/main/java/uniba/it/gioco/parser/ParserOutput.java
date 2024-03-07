/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.parser;

import java.io.Serializable;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Oggetto;

/**
 *
 * @author 39379
 * 
 */

//output del parser 
public class ParserOutput implements Serializable {
    
    private Comando comando;
    
    private Oggetto oggetto;
    
    private Oggetto oggettoInventario;

    public ParserOutput(Comando comando, Oggetto oggetto) {
        this.comando = comando;
        this.oggetto = oggetto;
    }

    public ParserOutput(Comando comando, Oggetto oggetto, Oggetto oggettoInventario) {
        this.comando = comando;
        this.oggetto = oggetto;
        this.oggettoInventario = oggettoInventario;
    }

    public Comando getComando() {
        return comando;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public Oggetto getOggettoInventario() {
        return oggettoInventario;
    }

    public void setOggettoInventario(Oggetto oggettoInventario) {
        this.oggettoInventario = oggettoInventario;
    }
    
    
    
         
}
