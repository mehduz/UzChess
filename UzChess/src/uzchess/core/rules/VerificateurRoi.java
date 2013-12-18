/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.constantes.TypeTour;
import uzchess.core.MoteurDeJeu;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

/**
 *
 * @author mehdi
 */
public class VerificateurRoi implements Deplacement {

    private MoteurDeJeu mdj;
    private Echiquier ech;
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);

        boolean condition1 = ech.isMenace(arr).isEmpty();
        boolean condition2 = ((new VerificateurReine().verifierDeplacement(dep, arr, false)) && (decLigne <= 1 && decColonne <= 1)) || verifierRoque(dep, arr, decLigne, noticeMove);

        if (condition1 && condition2) {
            if (noticeMove) {
                mdj.setRoiMoved(dep.getPiece().getCouleur());
            }
            return true;
        }
        return false;
    }

    private boolean verifierRoque(Case dep, Case arr, byte decLigne, boolean noticeMove) {
       
        Couleur col = dep.getPiece().getCouleur();
        Direction dir = ech.getDirection(dep, arr);

        boolean condition0 = dir == Direction.O;
        boolean condition1 = dir == Direction.E;
        boolean condition2 = (!(mdj.isRoiMoved(col)) && (ech.verifCasesInter(ech.getCasesInter(dep, arr))));
        boolean condition3 = (decLigne == 3) && (dir == Direction.O);
        boolean condition4 = (decLigne == 2) && (dir == Direction.E);
        boolean condition5 = (col == Couleur.BLANC) ? (condition3 && !mdj.isTourMoved(TypeTour.TBO)) || (condition4 && !mdj.isTourMoved(TypeTour.TBE))
                : (condition3 && !mdj.isTourMoved(TypeTour.TNO)) || (condition4 && !mdj.isTourMoved(TypeTour.TNE));

     
        if ((condition0 || condition1) && condition2 && condition5) {
            if (noticeMove) {
                if (dep.getPiece().getCouleur() == Couleur.BLANC) {
                    if (dir == Direction.O) {
                        mdj.setTourMoved(TypeTour.TBO, true);
                    } else {
                        mdj.setTourMoved(TypeTour.TBE, true);
                    }
                } else if (dir == Direction.O) {
                    mdj.setTourMoved(TypeTour.TNO, true);
                } else {
                    mdj.setTourMoved(TypeTour.TBE, true);
                }
                mdj.setTourMoved(TypeTour.TNO, true);
            }
            return true;
        }
        return false;
    } 
}
