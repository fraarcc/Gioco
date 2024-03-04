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
    private String nickname;
    private String password;
    private Stanza stanzaCorrente;
    private Inventario zaino;

    public Giocatore(String nickname, String password, Stanza stanzaCorrente, Inventario zaino) {
        this.nickname = nickname;
        this.password = password;
        this.stanzaCorrente = stanzaCorrente;
        this.zaino = zaino;
    }

   
    
}
