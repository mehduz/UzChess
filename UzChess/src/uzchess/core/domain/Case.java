package uzchess.core.domain;

import java.io.Serializable;
import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.constantes.PosLettres;

public class Case implements Serializable {

    private Couleur couleur;
    private byte ligne;
    private byte colonne;
    private Piece piece;
    private boolean ghosted;

    public Case(byte l, byte col, Couleur c) {

        ligne = l;
        colonne = col;
        couleur = c;
        piece = null;
        ghosted = false;
    }

    public boolean isGhosted() {
        return ghosted;
    }

    public void setGhosted(boolean ghosted) {
        this.ghosted = ghosted;
    }
    
    

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur c) {
        couleur = c;
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

        int lDep = ligne;
        int cDep = colonne;
        int lArr = arr.getLigne();
        int cArr = arr.getColonne();

        Direction dir;

        if (lArr == lDep && cArr > cDep) {
            dir = Direction.E;
        } else if (lArr == lDep && cArr < cDep) {
            dir = Direction.O;
        } else if (lArr > lDep && cArr == cDep) {
            dir = Direction.S;
        } else if (lArr < lDep && cArr == cDep) {
            dir = Direction.N;
        } else if (lArr < lDep && cArr > cDep) {
            dir = Direction.NE;
        } else if (lArr < lDep && cArr < cDep) {
            dir = Direction.NO;
        } else if (lArr > lDep && cArr > cDep) {
            dir = Direction.SE;
        } else if (lArr > lDep && cArr < cDep) {
            dir = Direction.SO;
        } else {
            dir = Direction.O;
        }
        return dir;
    }
    
    @Override
    public String toString() {
        return PosLettres.values()[ligne].toString() + String.valueOf(colonne);
    }
    
    
}
