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
    public void save(JeuEchecs partie) throws IOException{
        
        FileOutputStream fOut;
        ObjectOutputStream sOut;
        
        fOut = new FileOutputStream (partie.getNomPartie() + ".dat");
        sOut = new ObjectOutputStream (fOut);
        sOut.writeObject(partie);
        sOut.flush ();
        sOut.close ();
        fOut.close ();
      
    }
    
    @Override
    public JeuEchecs load(Object... criterias) throws IOException, ClassNotFoundException{
          
        FileInputStream fIn; 
        ObjectInputStream sIn;
        JeuEchecs p = null;
       
        fIn = new FileInputStream ((String)criterias[0]);
        sIn = new ObjectInputStream (fIn);
        p = (JeuEchecs) sIn.readObject ();
        sIn.close ();
        fIn.close ();
      
        return p;
    }
   
    /*@Override
    public void delete(Object... criterias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
