/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

/**
 *
 * @author 20130317
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

        if (verifierNormal(dep, arr, decLigne, decColonne) || verifierRoque(dep, arr)) {
            noticeKingMove(dep.getCouleur());
            return true;
        }

        return false;
    }

    private boolean verifierNormal(Case dep, Case arr, int decLigne, int decColonne) {
        return (new VerificateurReine().verifierDeplacement(dep, arr)) && (decLigne <= 1 && decColonne <= 1);
    }

    private boolean verifierRoque(Case dep, Case arr) {

        Echiquier ech = Echiquier.getInstance();
        Couleur col = dep.getCouleur();
        Direction dir = ech.getDirection(dep, arr);

        if (dir != Direction.O && dir != Direction.E) {
            return false;
        }
        if ((ech.isRoiMoved(col)) || (ech.verifCasesInter(ech.getCasesInter(dep, arr)))) {
            return false;
        }
        if( dir == Direction.O)
        return true;
    }

    private void noticeKingMove(Couleur color) {
        Echiquier ech = Echiquier.getInstance();
        ech.setRoiMoved(color);
    }

}
