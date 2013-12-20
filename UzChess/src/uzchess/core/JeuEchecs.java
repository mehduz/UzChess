package uzchess.core;

import uzchess.constantes.Couleur;
import uzchess.core.model.Echiquier;

public class JeuEchecs {

    public static JeuEchecs getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Couleur tour;
    private byte compteurCoups;
    private MoteurDeJeu moteurDeJeu;
    private Echiquier echiquier;
    
    private JeuEchecs() {
    }

    public void initialiser() {
        throw new UnsupportedOperationException();
    }

    public void quitter() {
        throw new UnsupportedOperationException();
    }

    public void abandonner() {
        throw new UnsupportedOperationException();
    }

    public void detecterFin() {
        throw new UnsupportedOperationException();
    }

    /* public boolean detecterFinNbC(){
    if( )
    }*/
    public void jouer() 
    {
        throw new UnsupportedOperationException();
    }

    public Couleur getTour() {
        return tour;
    } 

    public byte getCompteurCoups() {
        return compteurCoups;
    }

    public MoteurDeJeu getMoteurDeJeu() {
        return moteurDeJeu;
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }

    private static class SingletonHolder {

        private final static JeuEchecs INSTANCE = new JeuEchecs();
    }
}
