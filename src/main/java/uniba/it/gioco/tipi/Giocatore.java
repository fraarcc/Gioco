/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

/**
 *
 * @author Nikita
 */
public class Giocatore {
    private int idUtente;
    private String nickname;
    private Stanza stanzaCorrente;
    private Inventario zaino;

    public Giocatore(int idUtente,String nickname, Stanza stanzaCorrente, Inventario zaino) {
        this.idUtente = idUtente;
        this.nickname = nickname;
        this.stanzaCorrente = stanzaCorrente;
        this.zaino = zaino;
    }

   
    
}
