/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author 39379
 */
public class InitDatabase {
    
    private static final String CONNESSIONE_STR = "jdbc:h2:./res/database/Game_db";
    private static final String CREAZIONE_DB_UTENTI = "CREATE TABLE IF NOT EXISTS "
            + "UTENTI(ID_UTENTE INT PRIMARY KEY,"
            + "NICKNAME VARCHAR(255),"
            + "ID_PARTITA INT,"
            + "FOREIGN KEY (ID_PARTITA) REFERENCES PARTITA(ID_PARTITA));";
    private static final String CREAZIONE_DB_PARTITA = "CREATE TABLE IF NOT EXISTS "
            + "PARTITA(ID_PARTITA INT PRIMARY KEY,"
            + "STATO BOOLEAN,"
            + "DATA DATE);";
          
    
            
    private static Connection connessione;
    
            
    public static void creaConnessione(){
        
        Properties dbProps = new Properties();
        dbProps.setProperty("user", "user");
        dbProps.setProperty("password", "1234");
        
        try {
            connessione = DriverManager.getConnection(
                    CONNESSIONE_STR, dbProps);
      
            Statement createStm = connessione.createStatement();
            createStm.executeUpdate(CREAZIONE_DB_PARTITA);
            createStm.executeUpdate(CREAZIONE_DB_UTENTI);
         
            
            createStm.close();
        } catch (SQLException ex) {
            System.out.println("SQLException "
                + "\nMessage: " + ex.getMessage()
                + "\nSQL State: " + ex.getSQLState() 
                + "\nError Code: "+ ex.getErrorCode());
        }
    }
    
    
}
