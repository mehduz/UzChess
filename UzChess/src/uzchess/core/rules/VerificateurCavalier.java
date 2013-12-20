package uzchess.core.rules;

import uzchess.core.model.Case;

public class VerificateurCavalier implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

        byte depVer = (byte) Math.abs(ligCaseArr - ligCaseDep);
        byte depHor = (byte) Math.abs(colCaseArr - colCaseDep);

        return (depVer <= 2) && (depHor <= 2) && (depHor != depVer);
    }

}
