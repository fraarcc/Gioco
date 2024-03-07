/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Utils implements Serializable 
{
    //Carica la lista dele stopwords da file e la restituisce sotto forma di set di stringhe
    public static Set<String> caricaStopwords(File file) throws IOException 
    {
        Set<String> set = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while (reader.ready()) 
        {
            set.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
        return set;
    }


    public static List<String> parseString(String string, Set<String> stopwords) 
    {
        List<String> tokens = new ArrayList<>();
        
        //suddivide la stringa contenuta in string quando trova uno spazio
        String[] split = string.toLowerCase().split("\\s+");
        
        //quando una stringa nel vettore di stringhe split non è una stopword viene inserita nella lista tokens
        for (String t : split)
        {
            if (!stopwords.contains(t)) 
            {
                tokens.add(t);
            }
        }
        
        return tokens;
    }
}
