package uzchess.core;

import uzchess.constantes.Couleur;
import uzchess.constantes.RetourJouer;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;
import uzchess.core.model.Joueur;

public class JeuEchecs {

    public static JeuEchecs getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Couleur tour;
    private byte compteurCoups;
    private MoteurDeJeu moteurDeJeu;
    private Echiquier echiquier;
    private Joueur jn;
    private Joueur jb;

    private JeuEchecs() {
        compteurCoups = 0;
        tour = Couleur.BLANC;
    }

    public void setCompteurCoups(byte compteurCoups) {
        this.compteurCoups = compteurCoups;
    }

    public void incrementerCompteurCoups() {
        this.compteurCoups++;
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

    public RetourJouer detecterFin() {
        if (moteurDeJeu.detecterMat()) {
            return RetourJouer.ISMAT;
        }
        if (moteurDeJeu.detecterPat()) {
            return RetourJouer.ISPAT;
        }
        if (moteurDeJeu.detecterNul()) {
            return RetourJouer.ISNUL;
        }
        return RetourJouer.ISPOSSIBLE;
    }

    public RetourJouer jouer(Case dep, Case arr) {

        echiquier = JeuEchecs.getInstance().getEchiquier();
        RetourJouer retour = detecterFin();

        if (retour == RetourJouer.ISPOSSIBLE) {
            if (moteurDeJeu.verifierCoup(dep, arr)) {
                dep.getPiece().deplacer(dep, arr);
                return RetourJouer.ISPOSSIBLE;
            }
        }
        return retour;
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

    private static class SingletonHolder {

        private final static JeuEchecs INSTANCE = new JeuEchecs();
    }
}
