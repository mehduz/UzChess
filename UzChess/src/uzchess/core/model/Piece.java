package uzchess.core.model;

import uzchess.constantes.Couleur;
import uzchess.core.rules.Deplacement;

public class Piece {

    private Couleur couleur;
    private byte valeur;
    private Deplacement deplacement;

    public Couleur getCouleur() {
        return couleur;
    }

    public byte getValeur() {
        return valeur;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }
    
    public void deplacer() {
        throw new UnsupportedOperationException();
    }
    
    public void estPrise(){
        throw new UnsupportedOperationException();
    }

}
