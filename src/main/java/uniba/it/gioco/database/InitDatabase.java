/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.JButton;
import uniba.it.gioco.GameModel;

public class InitDatabase {

    private static final String CONNESSIONE_STR = "jdbc:h2:./res/database/Game_db";
    private static final String CREAZIONE_DB_PARTITA = "CREATE TABLE IF NOT EXISTS "
            + "PARTITA(id INT AUTO_INCREMENT PRIMARY KEY,"
            + "nickname VARCHAR(255) UNIQUE,"
            + "game_model BLOB,"
            + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

   private static final String INSERISCI_PARTITA = 
    "MERGE INTO PARTITA (nickname, game_model) " +
    "KEY(nickname) VALUES (?, ?)";


    private static final String SELEZIONA_PARTITA = "SELECT game_model FROM PARTITA WHERE id=?";

    private static final String ELIMINA_PARTITA = "DELETE FROM PARTITA WHERE id=?";
    
    private static final String SELEZIONA_GIOCATORE = "SELECT nickname FROM PARTITA WHERE nickname =?";

    private static Connection connessione;

    public static void creaConnessione() {
        Properties dbProps = new Properties();
        dbProps.setProperty("user", "user");
        dbProps.setProperty("password", "1234");

        try {
            connessione = DriverManager.getConnection(CONNESSIONE_STR, dbProps);

            Statement createStm = connessione.createStatement();

            createStm.executeUpdate(CREAZIONE_DB_PARTITA);

            createStm.close();
        } catch (SQLException ex) {
            System.out.println("SQLException "
                    + "\nMessage: " + ex.getMessage()
                    + "\nSQL State: " + ex.getSQLState()
                    + "\nError Code: " + ex.getErrorCode());
        }
    }

    public static void salvaPartita(String nickname, GameModel gameModel) {
        
        try {
            creaConnessione();
            PreparedStatement preparedStatement = connessione.prepareStatement(INSERISCI_PARTITA, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nickname);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(gameModel);
            byte[] gameModelBlob = baos.toByteArray();
            preparedStatement.setBytes(2, gameModelBlob);
            preparedStatement.executeUpdate();

            // Ottieni l'ID generato automaticamente dalla query di inserimento
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPartita = generatedKeys.getInt(1);
                System.out.println("Partita salvata con ID: " + idPartita);
                // Puoi fare qualcosa con l'ID generato, se necessario
            }

            preparedStatement.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
 public static boolean partitaEsistente(int idPartita) throws SQLException {
    boolean esistePartita = false;
    try {
        creaConnessione();
        PreparedStatement preparedStatement = connessione.prepareStatement(SELEZIONA_PARTITA);
        preparedStatement.setInt(1, idPartita);
        ResultSet resultSet = preparedStatement.executeQuery();
        esistePartita = resultSet.next(); // Se il ResultSet ha almeno una riga, la partita esiste
        preparedStatement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw ex; // Rilancia l'eccezione per gestirla nell'ambito chiamante, se necessario
    } 
    return esistePartita;
}


    public static GameModel caricaPartita(int idPartita) {
        GameModel gameModel = null;
        try {
            creaConnessione();
            PreparedStatement preparedStatement = connessione.prepareStatement(SELEZIONA_PARTITA);
            preparedStatement.setInt(1, idPartita);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                byte[] gameModelBlob = resultSet.getBytes("game_model");
                ByteArrayInputStream bais = new ByteArrayInputStream(gameModelBlob);
                ObjectInputStream ois = new ObjectInputStream(bais);
                gameModel = (GameModel) ois.readObject();
            }
            preparedStatement.close();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return gameModel;
    }

    public static void eliminaPartita(int idPartita) {
        try {
            creaConnessione();
            PreparedStatement preparedStatement = connessione.prepareStatement(ELIMINA_PARTITA);
            preparedStatement.setInt(1, idPartita);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public static ResultSet stampaPartiteDisponibiliResultSet() throws SQLException {
    creaConnessione();
    return connessione.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
            .executeQuery("SELECT id, nickname, timestamp FROM PARTITA");
}

    

    public static String getPartiteDisponibili() {
    StringBuilder partiteBuilder = new StringBuilder();
    try {
        creaConnessione();
        ResultSet resultSet = InitDatabase.stampaPartiteDisponibiliResultSet();
        while (resultSet.next()) {
            int idPartita = resultSet.getInt("id");
            String nickname = resultSet.getString("nickname");
            Timestamp timestamp = resultSet.getTimestamp("timestamp");
            partiteBuilder.append("ID partita: ").append(idPartita)
                    .append(", Nickname: ").append(nickname)
                    .append(", Timestamp: ").append(timestamp)
                    .append("\n\n");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        partiteBuilder.append("Errore durante il recupero delle partite disponibili.");
    }
    return partiteBuilder.toString();
}
    public static boolean verificaGiocatore(String nickname) {
        creaConnessione();
        try (PreparedStatement statement = connessione.prepareStatement(SELEZIONA_GIOCATORE)) {
            statement.setString(1, nickname);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
