package uzchess.core.rules;

import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;
import uzchess.core.domain.Echiquier;

public class VerificateurTour implements Deplacement {

    Echiquier ech;
    StatutTour st;

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

        if (ligCaseDep != ligCaseArr && colCaseDep != colCaseArr) {
            return false;
        }

        if (CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr))) {
            if(noticeMove){
                st.getTours().put( dep.getPiece(), true);
            } 
            return true;
        }
        return false;
    }

    public void setEch(Echiquier ech) {
        this.ech = ech;
    }

    public void setSt(StatutTour st) {
        this.st = st;
    }

}
