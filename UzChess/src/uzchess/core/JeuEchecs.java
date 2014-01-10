package uzchess.core;

import uzchess.constantes.Couleur;
import uzchess.core.domain.Case;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Joueur;
import uzchess.core.rules.VerificateurPion;

public class JeuEchecs {

    protected Couleur tour;
    protected byte compteurCoups;
    protected MoteurDeJeu moteurDeJeu;
    protected Echiquier echiquier;
    protected Joueur jn;
    protected Joueur jb;
    protected String nomPartie;

    protected boolean echec;
    private boolean mat;
    private boolean pat;
    private boolean nul;
    protected boolean invalide;

    public JeuEchecs() {

        compteurCoups = 0;
        tour = Couleur.BLANC;
        mat = false;
        echec = false;
        pat = false;
        nul = false;
        nomPartie = "";
        invalide = false;
    }

    public void initialiser(String j1, String j2) {
        Initializer.initialiserPartie(j1, j2, this);
    }

    protected void jouer(Case dep, Case arr) {
        invalide = true;
        if (moteurDeJeu.verifierCoup(dep, arr)) {

            compteurCoups = (dep.getPiece().getDeplacement() instanceof VerificateurPion || arr.getPiece() != null) ? 0 : (byte) (compteurCoups + 1);
            Joueur j = (tour == Couleur.BLANC) ? jb : jn;
            if(arr.getPiece() != null){
                //setter ici la valeur de la piece, à placer dans le verificateur
               //j.setScore((byte) (j.getScore() + arr.getPiece().getValeur()));
            }
            echiquier.deplacer(dep, arr);
            tour = (tour == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
            echec = moteurDeJeu.detecterEchec(tour);
            this.detecterFin();
            invalide = false;
        }
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

    public String getNomPartie() {
        return nomPartie;
    }

    
    public void setMoteurDeJeu(MoteurDeJeu mdj) {
        moteurDeJeu = mdj;
    }

    public void setEchiquier(Echiquier ech) {
        echiquier = ech;
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

    public Joueur getJoueur(Couleur c){
        if(c == Couleur.BLANC)
            return jb;
        return jn;
    }
    
    public void setJn(Joueur jn) {
        this.jn = jn;
    }
    
    public boolean isInvalide() {
        return invalide;
    }

    public void setInvalide(boolean inv) {
        invalide = inv;
    }

    public void setJb(Joueur jb) {
        this.jb = jb;
    }

    public void setNomPartie(String nomPartie) {
        this.nomPartie = nomPartie;
    }

}
