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
        
        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();
        
        byte decLigne = ( byte ) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = ( byte ) Math.abs(colCaseDep - colCaseArr);
        
        return new VerificateurReine().verifierDeplacement(dep,arr) &&  decLigne <= 1 && decColonne <= 1 ;
    }
    
}
