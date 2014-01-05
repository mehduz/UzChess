package uzchess.core.domain;

import uzchess.constantes.Couleur;
import uzchess.core.rules.Deplacement;
import uzchess.gui.RepPieceGraphique;

public class Piece implements Cloneable{

    private Couleur couleur;
    private byte valeur;
    private Deplacement deplacement;
    private RepPieceGraphique<Object> rep;

    public Piece() {

        couleur = null;
        deplacement = null;
        rep = null;
        valeur = -1;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setValeur(byte valeur) {
        this.valeur = valeur;
    }

    public void setDeplacement(Deplacement deplacement) {
        this.deplacement = deplacement;
    }

    public void setRep(RepPieceGraphique<Object> rep) {
        this.rep = rep;
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

    public RepPieceGraphique<Object> getRep() {
        return rep;
    }
}
