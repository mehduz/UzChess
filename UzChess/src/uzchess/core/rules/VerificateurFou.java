package uzchess.core.rules;

import uzchess.constantes.Direction;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurFou implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decVer = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decHor = (byte) Math.abs(colCaseDep - colCaseArr);

        //Si la case arrivé est sur le même ligne ou la même colonne
        Echiquier ech = Echiquier.getInstance();
        if (decVer != decHor) {
            return false;
        }
        Direction dir = (ligCaseArr > ligCaseDep) ? (colCaseArr > colCaseDep) ? Direction.SE : Direction.SO : (colCaseArr > colCaseDep) ? Direction.NE : Direction.NO;
        return ech.verifCasesInter(dep, arr, dir);

    }

}
