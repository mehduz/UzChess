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
import java.util.logging.Logger;
import uzchess.core.JeuEchecs;

public class DalFichier implements IDal<JeuEchecs, ArrayList<String> >, Serializable {
    
    private JeuEchecs partie;
    
    @Override
    public JeuEchecs charger(String nomFichier) {
        
        FileInputStream fIn; 
        ObjectInputStream sIn;
        JeuEchecs p = null;
        
        try {
            fIn = new FileInputStream (nomFichier);
            sIn = new ObjectInputStream (fIn);
            p = (JeuEchecs) sIn.readObject ();
            sIn.close ();
            fIn.close ();
        } 
        catch (IOException  | ClassNotFoundException e) {
            System.out.println (e);
        } 
        finally {
            sIn =  null;
            fIn = null;
        }
        
        return p;

    }
    
    @Override
    public void sauvegarder(String nomFichier) {

        FileOutputStream fOut;//Flux de sortie permettant d'écrire dans un fichier
        ObjectOutputStream sOut; //Permet d'écrire le contenu d'un objet Java de façon brut
        Writer tOut; // Equivalent du fwrite
        BufferedWriter bOut;

        try {
            fOut = new FileOutputStream (nomFichier + ".dat");
            sOut = new ObjectOutputStream (fOut);
            sOut.writeObject(partie);
            sOut.flush ();
            sOut.close ();
            fOut.close ();
        } catch (IOException e) {
            System.out.println (e);
            sOut = null;
            fOut = null;
        }
        
        try {
            tOut = new FileWriter ("parties.txt", true);
            bOut = new BufferedWriter (tOut);
            bOut.write (nomFichier);
            bOut.newLine ();
            bOut.close ();
        } catch (IOException e) {
            System.out.println (e);
            bOut = null;
            tOut = null;
        }
    }

    @Override
    public ArrayList<String> getListePartie() {
        
        Reader fIn;
        BufferedReader bIn;
        ArrayList<String> res = new ArrayList<>();
        
        try{ 
        fIn = new FileReader("parties.txt");
        bIn = new BufferedReader(fIn);
        String s = bIn.readLine();
        while(s != null || (s != null && s.isEmpty())){
            res.add(s);
            s = bIn.readLine();
        }
        bIn.close();
        fIn.close();
        
        }catch (IOException e) {
            System.out.println (e);
            bIn = null;
            fIn = null;
        }
        return res;
    }
    private static final Logger LOG = Logger.getLogger(DalFichier.class.getName());
    
}
