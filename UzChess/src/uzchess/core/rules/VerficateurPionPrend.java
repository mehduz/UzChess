/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.JeuEchecs;
import uzchess.core.model.Case;

/**
 *
 * @author user
 */
public class VerficateurPionPrend implements Deplacement{

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {
        
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
                        
        Couleur c = dep.getCouleur();
        Direction dir = JeuEchecs.getInstance().getEchiquier().getDirection(dep, arr);
        
        boolean condition1 = (c == Couleur.BLANC && ( dir == Direction.NE || dir == Direction.NO ));
        boolean condition2 = (c == Couleur.NOIR && ( dir == Direction.SE || dir == Direction.SO ));
        boolean condition3 = (Math.abs(ligArr - ligDep) == 1);
           
        return condition1 && condition2 && condition3;
    }
    
}
