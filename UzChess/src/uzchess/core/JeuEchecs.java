package uzchess.core;

import uzchess.core.model.Echiquier;
import uzchess.constantes.Couleur;

public class JeuEchecs {

    private Couleur tour;
    private short compteurCoups;
    private MoteurDeJeu moteurDeJeu;
    private Echiquier echiquier;

    public void initialiser() {
    }

    public void quitter() {
    }

    public void abandonner() {
    }

    public void detecterFin() {
    }

    public void jouer() {
    }

    public Couleur getTour() {
        return tour;
    }

    public int getCompteurCoups() {
        return compteurCoups;
    }

    public MoteurDeJeu getMoteurDeJeu() {
        return moteurDeJeu;
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }

}
