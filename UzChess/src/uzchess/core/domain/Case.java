package uzchess.core.domain;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;

public class Case {

    private Couleur couleur;
    private byte ligne; 
    private byte colonne;
    private Piece piece;

    public Case(byte ligne, byte colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public byte getLigne() {
        return ligne;
    }

    public byte getColonne() {
        return colonne;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setLigne(byte l) {
        ligne = l;
    }

    public void setColonne(byte c) {
        colonne = c;
    }

    public void setPiece(Piece p) {
        piece = p;
    }

    public Direction getDirection(Case arr) {

        int lDep = this.getLigne();
        int cDep = this.getColonne();
        int lArr = arr.getLigne();
        int cArr = arr.getColonne();

        Direction dir;

        if (lArr == lDep) {
            dir = (cArr > cDep) ? Direction.E : Direction.O;
        } else if (cArr == cDep) {
            dir = (lArr > lDep) ? Direction.S : Direction.N;
        } else if (lArr > lDep) {
            dir = (cArr > cDep) ? Direction.SE : Direction.SO;
        } else {
            dir = (cArr > cDep) ? Direction.NE : Direction.NO;
        }

        return dir;
    }

}
