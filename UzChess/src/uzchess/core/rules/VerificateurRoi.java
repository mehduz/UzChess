/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core.rules;

import uzchess.core.model.Case;

/**
 *
 * @author 20130317
 */
public class VerificateurRoi implements Deplacement{

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        
        byte ligneCaseDep = dep.getLigne();
        byte colonneCaseDep = dep.getColonne();
        byte ligneCaseArr = arr.getLigne();
        byte colonneCaseArr = arr.getColonne();
        
        byte decLigne = ( byte ) Math.abs(ligneCaseDep - ligneCaseArr);
        byte decColonne = ( byte ) Math.abs(colonneCaseDep - colonneCaseArr);
        
        return new VerificateurReine().verifierDeplacement(dep,arr) &&  decLigne <= 1 && decColonne <= 1 ;
    }
    
}
