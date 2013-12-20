package uzchess.core.model;

import uzchess.constantes.Couleur;
import static uzchess.constantes.Couleur.BLANC;
import uzchess.core.JeuEchecs;
import uzchess.core.rules.Deplacement;
import uzchess.core.rules.VerificateurPion;

public class Piece {

    private Couleur couleur;
    private byte valeur;
    private Deplacement deplacement;

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setValeur(byte valeur) {
        this.valeur = valeur;
    }

    public void setDeplacement(Deplacement deplacement) {
        this.deplacement = deplacement;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public byte getValeur() {
        return valeur;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

    public void deplacer(Case dep, Case arr) {
        JeuEchecs jeu = JeuEchecs.getInstance();
        if (arr.getPiece() != null) {
            arr.getPiece().estPrise();
            jeu.setCompteurCoups((byte) 0);
        }
        arr.setPiece(dep.getPiece());
        dep.setPiece(null);
        if (dep.getPiece().getDeplacement() instanceof VerificateurPion) {
            jeu.setCompteurCoups((byte) 0);
        }
        jeu.incrementerCompteurCoups();
    }

    public void estPrise() {
        JeuEchecs jeu = JeuEchecs.getInstance();
        Case c = (this.couleur == BLANC) ? jeu.getEchiquier().getPiecesB().remove(this) : jeu.getEchiquier().getPiecesN().remove(this);
    }

}
