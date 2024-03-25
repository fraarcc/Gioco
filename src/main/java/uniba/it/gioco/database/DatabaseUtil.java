package uniba.it.gioco.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DatabaseUtil {

    public static byte[] serializzaOggetto(Object oggetto) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(oggetto);
            byte[] datiOggetto = bos.toByteArray();
            oos.close();
            bos.close();
            return datiOggetto;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object deserializzaOggetto(byte[] datiOggetto) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(datiOggetto);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object oggetto = ois.readObject();
            ois.close();
            bis.close();
            return oggetto;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
