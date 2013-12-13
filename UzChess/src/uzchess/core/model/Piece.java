package uzchess.core.model;

import uzchess.constantes.Couleur;
import uzchess.core.rules.Deplacement;

public class Piece {

    private Couleur couleur;

    private byte valeur;
    private Case casePiece;
    private Deplacement deplacement;

    public void deplacer() {
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public byte getValeur() {
        return valeur;
    }

    public Case getCasePiece() {
        return casePiece;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

}
