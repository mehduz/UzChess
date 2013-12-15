package uzchess.core;

import uzchess.core.model.Echiquier;
import uzchess.constantes.Couleur;

public class JeuEchecs {

    private Couleur tour;
    private byte compteurCoups;
    private MoteurDeJeu moteurDeJeu;
    private Echiquier echiquier;
    
    private JeuEchecs() {
    }

    private static class SingletonHolder {

        private final static JeuEchecs instance = new JeuEchecs();
    }

    public static JeuEchecs getInstance() {
        return SingletonHolder.instance;
    }

    public void initialiser() {
        
    }

    public void quitter() {
        
    }

    public void abandonner() {
        
    }

    public void detecterFin() 
    {
        moteurDeJeu.detecterEchec();
        moteurDeJeu.detecterMat();
        moteurDeJeu.detecterPat();
    }

    public void jouer() {
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

}
