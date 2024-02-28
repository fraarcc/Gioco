/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

/**
 *
 * @author Nikita
 */
public class Oggetto {
    private final int id;
    private String descrizione;

    public Oggetto(int id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Oggetto{" + "id=" + id + ", descrizione=" + descrizione + '}';
    }
    
    
    
    
}
