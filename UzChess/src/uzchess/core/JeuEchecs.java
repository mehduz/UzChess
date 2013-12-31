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
    private String nomPartie;

    private boolean echec;
    private boolean mat;
    private boolean pat;
    private boolean nul;
    private boolean invalide;

    public JeuEchecs() {

        this.compteurCoups = 0;
        this.tour = Couleur.BLANC;
        this.mat = false;
        this.echec = false;
        this.pat = false;
        this.nul = false;
        this.nomPartie = "";
        this.invalide = false;
    }

    public void initialiser(String j1, String j2) {
        Initializer.initialiserPartie(j1, j2, this);
    }

    public void jouer(Case dep, Case arr) {

        if (moteurDeJeu.verifierCoup(dep, arr, true)) {

            compteurCoups = (dep.getPiece().getDeplacement() instanceof VerificateurPion || arr.getPiece() != null) ? 0 : (byte) (compteurCoups + 1);

            HashMap<Piece, Case> hm = (tour == Couleur.BLANC) ? echiquier.getPiecesB() : echiquier.getPiecesN();
            HashMap<Piece, Case> hmAdv = (tour == Couleur.BLANC) ? echiquier.getPiecesN() : echiquier.getPiecesB();
            Joueur j = (tour == Couleur.BLANC) ? jb : jn;
            hm.put(dep.getPiece(), arr);

            if (arr.getPiece() != null) {
                j.setScore((byte) (j.getScore() + arr.getPiece().getValeur()));
                hmAdv.remove(arr.getPiece());
            }
            
            dep.getPiece().deplacer(dep, arr);
            echec = moteurDeJeu.detecterEchec();
            tour = (tour == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
            this.detecterFin();
            this.invalide = false;
        }
        this.invalide = true;
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

    public boolean isInvalide() {
        return invalide;
    }

    public void setInvalide(boolean invalide) {
        this.invalide = invalide;
    }

    public void setJb(Joueur jb) {
        this.jb = jb;
    }

    public String getNomPartie() {
        return nomPartie;
    }
    
}
