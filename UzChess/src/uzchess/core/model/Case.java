package uzchess.core.model;

import uzchess.constantes.Couleur;

public class Case {

    private Couleur couleur;
    private byte ligne;
    private byte colonne;
    private Piece piece;

   

    public Couleur getCouleur() {
        return couleur;
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
    
     public void setCouleur(Couleur c) {
        couleur = c;
    }

}
