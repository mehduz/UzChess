package uzchess.core.rules;

import java.io.Serializable;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;

public class VerificateurFou implements Deplacement, Serializable {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr ) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decVer = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decHor = (byte) Math.abs(colCaseDep - colCaseArr);

        if (decVer != decHor) {
            return false;
        }
        return CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr));

    }

}
