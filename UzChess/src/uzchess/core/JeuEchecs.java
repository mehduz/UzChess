package uzchess.core;

import uzchess.constantes.Couleur;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;
import uzchess.core.model.Joueur;

public class JeuEchecs {

    private Couleur tour;
    private byte compteurCoups;
    private MoteurDeJeu moteurDeJeu;
    private Echiquier echiquier;
    private Joueur jn;
    private Joueur jb;
    
    private boolean echec;
    private boolean mat;
    private boolean pat;

    public JeuEchecs() {
        
        this.compteurCoups = 0;
        this.tour = Couleur.BLANC;
        this.mat = false;
        this.echec = false;
        this.pat = false;
    }
 

    public void initialiser() {
        Initializer.initialiserPartie(null, null, moteurDeJeu, echiquier, this);
    }
    
    public void jouer(Case dep, Case arr) {

    }
    
    public void detecterFin() {
        
    }

    public void quitter() {
       
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

    public void setMoteurDeJeu(MoteurDeJeu moteurDeJeu) {
        this.moteurDeJeu = moteurDeJeu;
    }

    public void setEchiquier(Echiquier echiquier) {
        this.echiquier = echiquier;
    }

    public Joueur getJn() {
        return jn;
    }

    public void setJn(Joueur jn) {
        this.jn = jn;
    }

    public Joueur getJb() {
        return jb;
    }

    public void setJb(Joueur jb) {
        this.jb = jb;
    }
}
