package uzchess.core.domain;

import java.io.Serializable;
import uzchess.constantes.Couleur;
import uzchess.core.rules.Deplacement;
import uzchess.gui.RepPieceGraphique;

public class Piece implements Serializable{

    private Couleur couleur;
    private Deplacement deplacement;
    private RepPieceGraphique<Object> rep;

    public Piece() {

        couleur = null;
        deplacement = null;
        rep = null;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
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

    public Deplacement getDeplacement() {
        return deplacement;
    }

    public RepPieceGraphique<Object> getRep() {
        return rep;
    }
}
