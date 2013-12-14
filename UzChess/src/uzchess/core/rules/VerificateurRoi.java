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
import uzchess.core.model.Echiquier;

/**
 *
 * @author mehdi
 */
public class VerificateurRoi implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);
            
        boolean condition1 = JeuEchecs.getInstance().getEchiquier().isMenace(arr).isEmpty();
        boolean condition2 = (verifierNormal(dep, arr, decLigne, decColonne) || verifierRoque(dep, arr, decLigne));
        
        if (condition1 && condition2){
            noticeKingMove(dep.getCouleur());
            return true; 
        }
        return false;
    }

    private boolean verifierNormal(Case dep, Case arr, int decLigne, int decColonne) {
        return (new VerificateurReine().verifierDeplacement(dep, arr)) && (decLigne <= 1 && decColonne <= 1);
    }

    private boolean verifierRoque(Case dep, Case arr, byte decLigne) {
        
        //throw new UnsupportedOperationException();
        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        Couleur col = dep.getCouleur();
        Direction dir = ech.getDirection(dep, arr);

        boolean condition1 = ( dir == Direction.O || dir == Direction.E );
        boolean condition2 = (!(ech.isRoiMoved(col)) && (ech.verifCasesInter(ech.getCasesInter(dep,arr))));
        boolean condition3 = ((decLigne == 3 && dir == Direction.O) || (decLigne == 2 && dir == Direction.E));
        
        return condition1 && condition2 && condition3;
    }

    private void noticeKingMove(Couleur color) {
        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        ech.setRoiMoved(color);
    }

}
