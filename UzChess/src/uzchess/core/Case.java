package uzchess.core;

public class Case {

  private boolean couleur;

  private int ligne;

    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }

    public boolean getCouleur() {
        return couleur;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

  private int colonne;

  private Piece piece;
      
}