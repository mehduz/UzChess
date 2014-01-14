package uzchess.core.rules;

import java.io.Serializable;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;

public class VerificateurTour implements Deplacement, Serializable {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

        if (ligCaseDep != ligCaseArr && colCaseDep != colCaseArr) {
            return false;
        }
        
        return CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr));
    }
}
