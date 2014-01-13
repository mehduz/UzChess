package uzchess.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import uzchess.core.JeuEchecs;

public class JeuEchecsDaoFile extends Dao<JeuEchecs> implements Serializable {

    public static final String SAVE_FOLDER = "UzChessSaves";
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    @Override
    public void save(JeuEchecs partie) throws DaoException {

        FileOutputStream fOut;
        ObjectOutputStream sOut;

        try {
            fOut = new FileOutputStream(SAVE_FOLDER + "/" + partie.getNomPartie() +dateFormat.format(new Date()) +".dat");
            sOut = new ObjectOutputStream(fOut);
            sOut.writeObject(partie);
            sOut.flush();
            sOut.close();
            fOut.close();
        } catch (IOException e) {
            throw new DaoException("Erreur lors de la sauvegarde : \n" + e.getStackTrace(), e);
        }

    }

    @Override
    public JeuEchecs load(Object... criterias) throws DaoException {

        FileInputStream fIn;
        ObjectInputStream sIn;
        JeuEchecs p = null;

        try {
            fIn = new FileInputStream((String) criterias[0]);
            sIn = new ObjectInputStream(fIn);
            p = (JeuEchecs) sIn.readObject();
            sIn.close();
            fIn.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new DaoException("Erreur lors du  chargement : \n" + e.getStackTrace(), e);  
        }
        return p;
    }

    @Override
    public void delete(Object... criterias) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }
}
