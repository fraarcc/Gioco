/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.storia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import uniba.it.gioco.tipi.Giocatore;
import uniba.it.gioco.tipi.Inventario;
import uniba.it.gioco.tipi.Oggetto;
import uniba.it.gioco.tipi.Stanza;



/**
 *
 * @author Nikita
 */

public class Init {
    private ObjectMapper objectMapper;

    public Init() {
        this.objectMapper = new ObjectMapper();
    }

    // Metodo per caricare un singolo oggetto JSON
    public <T> T loadJSON(String filePath, Class<T> type) throws IOException {
        return objectMapper.readValue(new File(filePath), type);
    }

    // Metodo per caricare una lista di oggetti JSON
    public <T> List<T> loadJSON(String resOggettijson, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(new File(resOggettijson), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // o gestire l'eccezione in modo appropriato
        }
    }
    
  //  public List<Oggetto> inizializzaOggetti() throws IOException{
    //    return loadJSON(".\\res\\Oggetti.json", new TypeReference<List<Oggetto>>() {});
    //}
    
    public List<Stanza> inizializzaStanze() throws IOException{
       return loadJSON(".\\res\\collegamentoStanze.json",new TypeReference<List<Stanza>>() {});
    }
    
    public Giocatore inizializzaGiocatore(int idUtente,String nickname,Stanza stanzaIniziale){
        //Aggiungere controllo nickname da database per adesso senza controlli
        return new Giocatore(idUtente,nickname,stanzaIniziale,new Inventario()); 
    }
}
