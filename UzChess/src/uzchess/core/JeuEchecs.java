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
        
        compteurCoups = 0;
        tour = Couleur.BLANC;
        mat = false;
        echec = false;
        pat = false;
    }
    
    public void setCompteurCoups(byte compteurCoups) {
        this.compteurCoups = compteurCoups;
    }

    public void incrementerCompteurCoups() {
        this.compteurCoups++;
    }

    public void initialiser() {
    
    }

    public void quitter() {
        throw new UnsupportedOperationException();
    }

    public void abandonner() {
        throw new UnsupportedOperationException();
    }

    public void detecterFin() {
        
    }

    public void jouer(Case dep, Case arr) {

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
