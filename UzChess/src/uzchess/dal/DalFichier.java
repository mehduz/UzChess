package uzchess.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import uzchess.core.JeuEchecs; 

public class DalFichier implements IDal<JeuEchecs, ArrayList<String> >, Serializable {
    
    @Override
    public JeuEchecs charger(String nomFichier) throws IOException, ClassNotFoundException{
        
        FileInputStream fIn; 
        ObjectInputStream sIn;
        JeuEchecs p = null;
      
        fIn = new FileInputStream (nomFichier);
        sIn = new ObjectInputStream (fIn);
        p = (JeuEchecs) sIn.readObject ();
        sIn.close ();
        fIn.close ();
      
        return p;

    }
    
    @Override
    public void sauvegarder(String nomFichier, JeuEchecs partie) throws IOException{

        FileOutputStream fOut;
        ObjectOutputStream sOut;
        Writer tOut; 
        BufferedWriter bOut;
        
        fOut = new FileOutputStream (nomFichier + ".dat");
        sOut = new ObjectOutputStream (fOut);
        sOut.writeObject(partie);
        sOut.flush ();
        sOut.close ();
        fOut.close ();
       
        tOut = new FileWriter ("parties.txt", true);
        bOut = new BufferedWriter (tOut);
        bOut.write (nomFichier);
        bOut.newLine ();
        bOut.close ();
       
    }

    @Override
    public ArrayList<String> getListePartie() throws IOException{
        
        Reader fIn;
        BufferedReader bIn;
        ArrayList<String> res = new ArrayList<>();
        
        fIn = new FileReader("parties.txt");
        bIn = new BufferedReader(fIn);
        String s = bIn.readLine();
        while(s != null || (s != null && s.isEmpty())){
            res.add(s);
            s = bIn.readLine();
        }
        bIn.close();
        fIn.close();
     
        return res;
    }
}
