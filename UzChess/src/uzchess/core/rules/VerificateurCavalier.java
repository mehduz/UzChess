package uzchess.core.rules;

import uzchess.core.model.Case;

public class VerificateurCavalier implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

        byte depVertical = (byte) Math.abs(ligCaseArr - ligCaseDep);
        byte depHorizontal = (byte) Math.abs(colCaseArr - colCaseDep);

        return (depVertical <= 2) && (depHorizontal <= 2) && (depHorizontal != depVertical);
    }

}
