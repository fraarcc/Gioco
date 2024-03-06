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
    private Inventario inventario;

    public Giocatore(int idUtente,String nickname, Stanza stanzaCorrente, Inventario zaino) {
        this.idUtente = idUtente;
        this.nickname = nickname;
        this.stanzaCorrente = stanzaCorrente;
        this.inventario = new Inventario();
    }

    public int getIdUtente() {
        return idUtente;
    }

    public String getNickname() {
        return nickname;
    }

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public Inventario getInventario() {
        return inventario;
    }
    
    public void setStanzaCorrente(Stanza stanzaCorrente){
        this.stanzaCorrente = stanzaCorrente;
    }
    
    @Override
    public String toString() {
        return "Giocatore{" +
                "id=" + idUtente +
                ", nome='" + nickname + '\'' +
                ", stanzaAttuale=" + stanzaCorrente +
                ", inventario=" + inventario +
                '}';
    }

   
    
}
