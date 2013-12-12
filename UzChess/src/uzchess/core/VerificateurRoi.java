/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core;

/**
 *
 * @author 20130317
 */
public class VerificateurRoi extends Deplacement{

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        
        int ligneCaseDep = dep.getLigne();
        int colonneCaseDep = dep.getColonne();
        int ligneCaseArr = arr.getLigne();
        int colonneCaseArr = arr.getColonne();
        
        int decLigne = Math.abs(ligneCaseDep - ligneCaseArr);
        int decColonne = Math.abs(colonneCaseDep - colonneCaseArr);
        
        return new VerificateurReine().verifierDeplacement(dep,arr) && ( decLigne + decColonne <= 2 );
    }
    
}
