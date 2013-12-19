package uzchess.core.rules;

import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;
import uzchess.core.model.CheckCasesInterUtility;
import uzchess.core.model.CaseInterUtility;

public class VerificateurFou implements Deplacement {

    
    private Echiquier ech;
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decVer = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decHor = (byte) Math.abs(colCaseDep - colCaseArr);

        //Si la case arrivé est sur le même ligne ou la même colonne
        if (decVer != decHor) {
            return false;
        }
        return CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr));

    }

}
