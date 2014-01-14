package uzchess.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import uzchess.model.JeuEchecsModel;

public class JeuEchecsDaoFile extends Dao<JeuEchecsModel> implements Serializable {

    public static final String SAVE_FOLDER = "UzChessSaves";
    
    @Override
    public void save(JeuEchecsModel partie) throws DaoException {

        FileOutputStream fOut;
        ObjectOutputStream sOut;

        try {
            
            String fileName = partie.getNomPartie() + ".dat";
            File file = new File(SAVE_FOLDER, fileName);
            fOut = new FileOutputStream( file); 
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
    public JeuEchecsModel load(String fileName) throws DaoException {

        FileInputStream fIn;
        ObjectInputStream sIn;
        JeuEchecsModel p = null;

        try {
            File file = new File(SAVE_FOLDER, fileName);
            fIn = new FileInputStream( file );
            sIn = new ObjectInputStream(fIn);
            p = (JeuEchecsModel) sIn.readObject();
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
