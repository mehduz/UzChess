package uzchess.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import uzchess.core.JeuEchecs;

public class JeuEchecsDaoFile extends Dao<JeuEchecs> implements Serializable {

    @Override
    public void save(JeuEchecs partie) throws DaoException {

        FileOutputStream fOut;
        ObjectOutputStream sOut;

        try {
            fOut = new FileOutputStream(partie.getNomPartie() + ".dat");
            sOut = new ObjectOutputStream(fOut);
            sOut.writeObject(partie);
            sOut.flush();
            sOut.close();
            fOut.close();
        } catch (IOException e) {
            throw new DaoException("Erreur lors de la sauvegarde" + e.getMessage());
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
            throw new DaoException(e.getMessage()); 
        }
        return p;
    }

    @Override
    public void delete(Object... criterias) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }
}
