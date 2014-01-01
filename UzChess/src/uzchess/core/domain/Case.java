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

        if (lArr == lDep && cArr > cDep ) {
            dir = Direction.E ;
        }
        else if(lArr == lDep && cArr < cDep){
            dir = Direction.O;
        }  
        else if(lArr > lDep && cArr == cDep){
            dir  = Direction.S;
        }
        else if(lArr < lDep && cArr == cDep){
            dir = Direction.N;
        }
        else if (lArr < lDep && cArr > cDep){
            dir = Direction.NE;
        }
        else if (lArr < lDep && cArr < cDep){
            dir = Direction.NO;
        }
        else if (lArr > lDep && cArr > cDep){
            dir = Direction.SE;
        }
        else if (lArr > lDep && cArr < cDep){
            dir = Direction.SO;
        }
        else
        {
            dir = Direction.O;
        }
        return dir;
    }

}
