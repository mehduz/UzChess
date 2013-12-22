package uzchess.dal;

import java.io.IOException;
import uzchess.core.JeuEchecs;

public interface IDal < T, S> {
    
    public T charger(String nom) throws IOException, ClassNotFoundException;
    public void sauvegarder(String nom, JeuEchecs jeu) throws IOException;
    public S getListePartie() throws IOException;
    
}