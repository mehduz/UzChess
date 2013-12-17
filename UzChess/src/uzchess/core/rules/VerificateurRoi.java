/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.constantes.TypeTour;
import uzchess.core.JeuEchecs;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

/**
 *
 * @author mehdi
 */
public class VerificateurRoi implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);

        Echiquier ech = JeuEchecs.getInstance().getEchiquier();

        boolean condition1 = ech.isMenace(arr).isEmpty();
        boolean condition2 = verifierNormal(dep, arr, decLigne, decColonne) || verifierRoque(dep, arr, decLigne, noticeMove);

        if (condition1 && condition2) {
            if (noticeMove) {
                ech.setRoiMoved(dep.getCouleur());
            }
            return true;
        }
        return false;
    }

    private boolean verifierNormal(Case dep, Case arr, byte decLigne, byte decColonne) {
        return (new VerificateurReine().verifierDeplacement(dep, arr, false)) && (decLigne <= 1 && decColonne <= 1);
    }

    private boolean verifierRoque(Case dep, Case arr, byte decLigne, boolean noticeMove) {

        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        Couleur col = dep.getCouleur();
        Direction dir = ech.getDirection(dep, arr);

        boolean condition0 = dir == Direction.O;
        boolean condition1 = dir == Direction.E;
        boolean condition2 = (!(ech.isRoiMoved(col)) && (ech.verifCasesInter(ech.getCasesInter(dep, arr))));
        boolean condition3 = (decLigne == 3) && (dir == Direction.O);
        boolean condition4 = (decLigne == 2) && (dir == Direction.E);
        boolean condition5 = (col == Couleur.BLANC) ? (condition3 && !ech.isTourMoved(TypeTour.TBO)) || (condition4 && !ech.isTourMoved(TypeTour.TBE))
                : (condition3 && !ech.isTourMoved(TypeTour.TNO)) || (condition4 && !ech.isTourMoved(TypeTour.TNE));

        //Attention la tour doit se déplacer, et la piece avec laquelle on swappe doit obligatoirement être une Tour
        if ((condition0 || condition1) && condition2 && condition5) {
            if (noticeMove) {
                if (dep.getCouleur() == Couleur.BLANC) {
                    if (Direction == Direction.O) {
                        ech.setTourMoved(TypeTour.TBO, true);
                    } else {
                        ech.setTourMoved(TypeTour.TBE, true);
                    }
                } else if (Direction == Direction.O) {
                    ech.setTourMoved(TypeTour.TNO, true);
                } else {
                    ech.setTourMoved(TypeTour.TBE, true);
                }
                ech.setTourMoved(TypeTour.TNO, true);
            }
            return true;
        }
        return false;
    }
}
