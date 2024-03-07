/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.parser;


import uniba.it.gioco.tipi.Oggetto;
import uniba.it.gioco.tipi.Comando;
import uniba.it.gioco.tipi.Utils;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author gabri
 */
public class Parser implements Serializable 
{

    private final Set<String> stopwords;

    //Costruttore
    public Parser(Set<String> stopwords) 
    {
        this.stopwords = stopwords;
    }

    
    /*
    Se il comando passato in input è presente nella lista dei comandi passata in input oppure
    è presente tra gli alias di un comando, allora viene restituita la posizione del comando nella lista.
    (La posizione nella lista sarebbe un indice intero)
    */
    private int checkComando(String token, List<Comando> comandi) 
    {
        for (int i = 0; i < comandi.size(); i++) 
        {
            if (comandi.get(i).getNome().equals(token) || comandi.get(i).getAlias().contains(token)) 
            {
                return i;
            }
        }
        return -1;
    }

    
    /*
    Se l'oggetto passato in input è presente nella lista degli oggetti passata in input oppure
    è presente tra gli alias di un oggetto, allora viene restituita la posizione dell'oggetto nella lista.
    (La posizione nella lista sarebbe un indice intero)
    */
    private int checkOggetto(String token, List<Oggetto> oggetti) 
    {
        for (int i = 0; i < oggetti.size(); i++) 
        {
            if (oggetti.get(i).getNome().equalsIgnoreCase(token)) 
            {
                return i;
            }
        }
        return -1;
    }
    

    /* ATTENZIONE: il parser è implementato in modo abbastanza independente dalla lingua, ma riconosce solo 
    * frasi semplici del tipo <azione> <oggetto> <oggetto>. Eventuali articoli o preposizioni vengono semplicemente
    * rimossi.
     */
    public ParserOutput parse(String comando, List<Comando> comandi, List<Oggetto> oggetti_stanza_corrente, List<Oggetto> inventario) 
    {       
        //Il comando dell'utente viene suddiviso in token(cioè le parole separate da spazio nel comando originale).
        //Il comando suddiviso in token viene ripulito dalle stopwords avendo così una lista di token priva di stopwords 
        List<String> tokens = Utils.parseString(comando, stopwords);
        
        if (!tokens.isEmpty())//se l'utente ha fornito qualcosa in input
        {
            //verifica che la prima parte di comando sia un' azione
            int idComando = checkComando(tokens.get(0), comandi);
            
            //se la prima parte di comando è un' azione
            if (idComando > -1)
            {
                //se la parte seguente di comando non è vuota
                if (tokens.size() > 1)
                {
                    //verifica che la seconda parte di comando sia un oggetto
                    int idOggetto = checkOggetto(tokens.get(1), oggetti_stanza_corrente);
                    
                    int idOggettoInventario = -1;
                    
                     //se la seconda parte di comando non  è un oggetto, ma ci sono più di 3 parti di comando
                    if (idOggetto < 0 && tokens.size() > 2) 
                    {
                        //verifica che la terza parte sia un oggetto
                        idOggetto = checkOggetto(tokens.get(2), oggetti_stanza_corrente);
                    }
                    
                    if (idOggetto < 0)
                    {
                        idOggettoInventario = checkOggetto(tokens.get(1), inventario);
                        
                        if (idOggettoInventario < 0 && tokens.size() > 2)
                        {
                            idOggettoInventario = checkOggetto(tokens.get(2), inventario);
                        }
                    }
                    
                    if (idOggetto > -1 && idOggettoInventario > -1) 
                    {
                        return new ParserOutput(comandi.get(idComando), oggetti_stanza_corrente.get(idOggetto), inventario.get(idOggettoInventario));
                    }
                    else if (idOggetto > -1)
                    {
                        return new ParserOutput(comandi.get(idComando), oggetti_stanza_corrente.get(idOggetto), null);
                    }
                    else if (idOggettoInventario > -1)
                    {
                        return new ParserOutput(comandi.get(idComando), null, inventario.get(idOggettoInventario));
                    }
                    else
                    {
                        return new ParserOutput(comandi.get(idComando), null, null);
                    }
                }
                else
                {
                    return new ParserOutput(comandi.get(idComando), null);
                }         
            }
            else
            {
                return new ParserOutput(null, null);
            }
        }
        else
        {
            return null;
        }
    }
}
