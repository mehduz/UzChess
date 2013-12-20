package uzchess.dal;

public interface IDal < T, S> {
    
    public T charger(String nomFichier);
    public void sauvegarder(String nomFichier);
    public S getListePartie();
    
}
