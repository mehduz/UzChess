/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core;

import uzchess.constantes.Couleur;
import uzchess.core.model.Echiquier;
import uzchess.core.model.Joueur;

/**
 *
 * @author user
 */
public class Initializer {
    
    
    
    public static void initialiserPartie(String njb, String njn){
        
        JeuEchecs jeu = null;
        
        Joueur jb = new Joueur(Couleur.BLANC, njb, (byte)0);
        Joueur jn = new Joueur(Couleur.NOIR, njn, (byte)0);
        
        
        Echiquier ech = new Echiquier();
        MoteurDeJeu mdj = new MoteurDeJeu();
        
    }
}