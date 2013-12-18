package uzchess.core.model;

import uzchess.constantes.Couleur;

public class Joueur {

    private Couleur couleur;
    private String pseudo;
    private byte score;

    public Couleur getCouleur() {
        return couleur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public byte getScore() {
        return score;
    }
}
