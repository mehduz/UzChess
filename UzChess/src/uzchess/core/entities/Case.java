package uzchess.core.entities;

import uzchess.constantes.Couleur;

public class Case {

    private Couleur couleur;
    private byte ligne;
    private byte colonne;
    private Piece piece;

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

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

    public void setLigne(byte ligne) {
        this.ligne = ligne;
    }

    public void setColonne(byte colonne) {
        this.colonne = colonne;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
