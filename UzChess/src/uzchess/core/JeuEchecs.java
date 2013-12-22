package uzchess.core;

import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.core.domain.Case;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Joueur;
import uzchess.core.domain.Piece;
import uzchess.core.rules.VerificateurPion;

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
    private boolean nul;

    public JeuEchecs() {

        this.compteurCoups = 0;
        this.tour = Couleur.BLANC;
        this.mat = false;
        this.echec = false;
        this.pat = false;
        this.nul = false;
    }

    public void initialiser(String j1, String j2) {
        Initializer.initialiserPartie(j1, j2, moteurDeJeu, echiquier, this);
    }

    public boolean jouer(Case dep, Case arr) {

        if (moteurDeJeu.verifierCoup(dep, arr)) {

            compteurCoups = (dep.getPiece().getDeplacement() instanceof VerificateurPion || arr != null) ? 0 : (byte) (compteurCoups + 1);

            HashMap<Piece, Case> hm = (tour == Couleur.BLANC) ? echiquier.getPiecesB() : echiquier.getPiecesN();
            Joueur j = (tour == Couleur.BLANC) ? jb : jn;
            hm.put(dep.getPiece(), arr);

            if (arr != null) {
                jb.setScore((byte) (jb.getScore() + arr.getPiece().getValeur()));
                hm.remove(arr.getPiece());
            }
            
            dep.getPiece().deplacer(dep, arr);
            echec = moteurDeJeu.detecterEchec();
            tour = (tour == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
            this.detecterFin();

            return true;
        }
        return false;
    }

    public void detecterFin() {

        if (echec) {
            mat = moteurDeJeu.detecterMat();
        }
        pat = moteurDeJeu.detecterPat();
        nul = compteurCoups >= 50;
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

    public boolean isEchec() {
        return echec;
    }

    public boolean isMat() {
        return mat;
    }

    public boolean isPat() {
        return pat;
    }

    public boolean isNul() {
        return nul;
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
