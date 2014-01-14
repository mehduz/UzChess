package uzchess.core.domain;

import java.io.Serializable;
import uzchess.constantes.Couleur;

public class Joueur implements Serializable{

    private Couleur couleur;
    private String pseudo;
    private byte score;

    public Joueur(Couleur couleur, String pseudo, byte score) {
        this.couleur = couleur;
        this.pseudo = pseudo;
        this.score = score;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public byte getScore() {
        return score;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setScore(byte score) {
        this.score = score;
    }

}
