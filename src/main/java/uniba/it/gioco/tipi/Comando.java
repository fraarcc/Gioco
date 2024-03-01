/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



/**
 *
 * @author 39379
 */
public class Comando implements Serializable {
    
  private final TipoComando tipo;
  
  private final String nome;

  private Set<String> alias;
            
    //Costruttori
    public Comando(TipoComando tipo, String nome) 
    {
        this.tipo = tipo;
        this.nome = nome;
    }
    

    public Comando(TipoComando tipo, String nome, Set<String> alias) 
    {
        this.tipo = tipo;
        this.nome = nome;
        this.alias = alias;
    }
    //  
    
    
    public String getNome() 
    {
        return nome;
    }

    
    public Set<String> getAlias()
    {
        return alias;
    }
    

    public void setAlias(Set<String> alias)
    {
        this.alias = alias;
    }

    
    public void setAlias(String[] alias)
    {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    
    public TipoComando getTipo()
    {
        return tipo;
    }       
        
    

   }