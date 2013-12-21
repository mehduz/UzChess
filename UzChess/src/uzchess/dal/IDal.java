package uzchess.dal;

import uzchess.core.JeuEchecs;

public interface IDal < T, S> {
    
    public T charger(String nom);
    public void sauvegarder(String nom, JeuEchecs jeu);
    public S getListePartie();
    
}